package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.stub.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private final Paragraph myParagraph = new Paragraph("Hello World!");
    private final Paragraph paragraph2 = new Paragraph("I am a thing");
    private final Input in = new Input("text", "Hello World", "AAAAAAAA");
    private final Form libForm = Form.builder()
            .id("myForm")
            .addClass("form")
            .addField(in)
            .build();
    private final FormWithInputs stubForm = new FormWithInputs();
    private final ListItemStub listItem = new ListItemStub("Heyo");
    private final ListStub list = new ListStub(listItem);
    private final ListItemStub shallowListItem = new ListItemStub("This is a thing");
    private final Div deeplyNestedDiv = Div.builder()
            .id("myDiv")
            .build();
    private final Div directParent = Div.builder()
            .addChild(deeplyNestedDiv)
            .addChild(list)
            .build();
    private final Div ancestorDiv = Div.builder()
            .addChild(directParent)
            .addChild(shallowListItem)
            .build();
    private final HTMLDocument testDoc = HTMLDocument.builder()
            .addElement(stubForm)
            .addElement(myParagraph)
            .addElement(libForm)
            .addElement(ancestorDiv)
            .addElement(paragraph2)
            .build();

    @Test
    public void testGroupQueryIsFlattenedList() {

        testDoc.querySelector("*, *").forEach(
                element -> assertFalse(element instanceof List)
        );

    }

    @Test
    public void testFindAllParagraphs() {

        assertEquals(List.of(myParagraph, paragraph2), testDoc.querySelector("p"));

    }

    @Test
    public void testQueryById() {

        assertEquals(List.of(libForm), testDoc.querySelector("#myForm"));

    }

    @Test
    public void testQueryByClass() {

        assertEquals(List.of(libForm), testDoc.querySelector(".form"));

    }

    @Test
    public void testDescendentSelector() {

        assertEquals(List.of(in), testDoc.querySelector("#myForm input"));

    }

    @Test
    public void testDeeplyNestedDescendent() {

        assertEquals(List.of(deeplyNestedDiv), testDoc.querySelector("div div #myDiv"));

    }

    @Test
    public void testWildCardSelector() {

        List<AbstractUIElement> allElements = testDoc.getElements();
        allElements.addAll(
                allElements.stream()
                        .map(HTMLDocument::getAllDescendents)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
        assertEquals(allElements, testDoc.querySelector("*"));

    }

    @Test
    public void testDirectDescendentSelector() {

        assertEquals(List.of(directParent, deeplyNestedDiv), testDoc.querySelector("div > div"));

    }

    @Test
    public void testDescendentDivs() {

        assertEquals(List.of(deeplyNestedDiv, directParent), testDoc.querySelector("div div"));

    }

    @Test
    public void testGrandChildDiv() {

        assertEquals(List.of(deeplyNestedDiv), testDoc.querySelector("div div div"));

    }

    @Test
    public void testDirectListItemChild() {

        assertEquals(List.of(shallowListItem), testDoc.querySelector("div > li"));

    }

    @Test
    public void testDescendentListItems() {

        assertEquals(List.of(listItem, shallowListItem), testDoc.querySelector("div li"));

    }

    @Test
    public void testNextSiblingSelector() {

        assertEquals(List.of(ancestorDiv), testDoc.querySelector("form + div"));

    }

    @Test
    public void testParagraphsAfterForms() {

        assertEquals(List.of(myParagraph), testDoc.querySelector("form + p"));

    }

    @Test
    public void testFormAfterParagraph() {

        assertEquals(List.of(libForm), testDoc.querySelector("p + form"));

    }

}
