package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.stub.Div;
import io.john.amiscaray.stir.stub.FormWithInputs;
import io.john.amiscaray.stir.stub.Paragraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private final Paragraph myParagraph = new Paragraph("Hello World!");
    private final Input in = new Input("text", "Hello World", "AAAAAAAA");
    private final Form libForm = Form.builder()
            .id("myForm")
            .addClass("form")
            .addField(in)
            .build();

    private final FormWithInputs stubForm = new FormWithInputs();

    private final Div deeplyNestedDiv = Div.builder()
            .id("myDiv")
            .build();

    private final Div directParent = Div.builder()
            .addChild(deeplyNestedDiv)
            .build();

    private final Div ancestorDiv = Div.builder()
            .addChild(directParent)
            .build();

    private final HTMLDocument testDoc = HTMLDocument.builder()
            .addElement(stubForm)
            .addElement(myParagraph)
            .addElement(libForm)
            .addElement(ancestorDiv)
            .build();

    @Test
    public void testGroupQueryIsFlattenedList() {

        testDoc.querySelector("*, *").forEach(
                element -> assertFalse(element instanceof List)
        );

    }

    @Test
    public void testFindAllParagraphs() {

        assertEquals(List.of(myParagraph), testDoc.querySelector("p"));

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

        assertEquals(testDoc.getElements(), testDoc.querySelector("*"));

    }

    @Test
    public void testDirectDescendentSelector() {

        assertEquals(List.of(directParent), testDoc.querySelector("div > div"));

    }

    @Test
    public void testDescendentDivs() {

        assertEquals(List.of(deeplyNestedDiv, directParent), testDoc.querySelector("div div"));

    }

    @Test
    public void testGrandChildDiv() {

        assertEquals(List.of(deeplyNestedDiv), testDoc.querySelector("div div div"));

    }

}
