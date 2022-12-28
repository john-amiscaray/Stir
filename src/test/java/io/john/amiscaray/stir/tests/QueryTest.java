package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.stub.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static io.john.amiscaray.stir.setup.StirAssertions.*;

public class QueryTest {

    private final Paragraph myParagraph = new Paragraph("Hello World!");
    private final Paragraph paragraph2 = Paragraph.builder()
            .id("p2")
            .cssClass("content")
            .cssClass("spooky")
            .content("This is some spooky content")
            .build();
    private final Paragraph paragraph3 = Paragraph.builder()
            .cssClass("content")
            .cssClass("spooky")
            .content("Other spooky content")
            .build();
    private final Input in = new Input("someIn", "text", "Hello World", "AAAAAAAA");
    private final Form libForm = Form.builder()
            .id("myForm")
            .cssClass("form")
            .field(in)
            .build();
    private final FormWithInputs stubForm = new FormWithInputs();
    private final ListItemStub listItem = new ListItemStub("Heyo");
    private final ListStub list = new ListStub(listItem);
    private final ListItemStub shallowListItem = new ListItemStub("This is a thing");
    private final Div deeplyNestedDiv = Div.builder()
            .id("myDiv")
            .build();
    private final Div directParent = Div.builder()
            .child(deeplyNestedDiv)
            .child(paragraph3)
            .child(list)
            .build();
    private final Div ancestorDiv = Div.builder()
            .child(directParent)
            .child(shallowListItem)
            .build();
    private final Div emptyDiv = Div.builder()
            .build();
    private final HTMLDocument testDoc = HTMLDocument.builder()
            .element(stubForm)
            .element(myParagraph)
            .element(libForm)
            .element(ancestorDiv)
            .element(paragraph2)
            .element(emptyDiv)
            .build();

    @Test
    public void testGroupQueryIsFlattenedList() {

        testDoc.querySelector("*, *").forEach(
                element -> assertFalse(element instanceof List)
        );

    }

    @Test
    public void testFindAllParagraphs() {

        assertEquals(List.of(myParagraph, paragraph2, paragraph3), testDoc.querySelector("p"));

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

        List<AbstractUIElement> expected = new ArrayList<>(testDoc.getElements());
        expected.addAll(
                expected.stream()
                        .map(HTMLDocument::getAllDescendents)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
        );
        List<AbstractUIElement> actual = testDoc.querySelector("*");
        assertListEquality(expected, actual);

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

    @Test
    public void testTildeOperator() {

        assertEquals(List.of(ancestorDiv, emptyDiv), testDoc.querySelector("#myForm ~ div"));

    }

    @Test
    public void testListItemsAfterDiv() {

        assertEquals(List.of(shallowListItem), testDoc.querySelector("div ~ li"));

    }

    @Test
    public void testIdAndClass(){

        assertEquals(List.of(libForm), testDoc.querySelector("#myForm.form"));

    }

    @Test
    public void testMultipleClasses(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector(".content.spooky"));

    }

    @Test
    public void testNestedMultiClass(){

        assertEquals(List.of(paragraph3), testDoc.querySelector("div .content.spooky"));

    }

    @Test
    public void testClassAttributeSelector(){

        assertEquals(List.of(libForm), testDoc.querySelector("form[class=form]"));

    }

    @Test
    public void testClassAttributeContains(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector("p[class*=spooky]"));

    }

    @Test
    public void testClassAttributeTildeTent(){

        assertEquals(List.of(), testDoc.querySelector("p[class~=tent]"));

    }

    @Test
    public void testClassAttributeContainsTent(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector("p[class*=tent]"));

    }

    @Test
    public void testClassAttributeTildeSpooky(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector("p[class~=spooky]"));

    }

    @Test
    public void testIdStartsWithMy(){

        assertEquals(List.of(libForm, deeplyNestedDiv), testDoc.querySelector("*[id^=my]"));

    }

    @Test
    public void testIdEndsWithRM(){

        assertEquals(List.of(libForm), testDoc.querySelector("*[id$=rm]"));

    }

    @Test
    public void testSelectAnyWithClasses(){

        assertEquals(List.of(libForm, paragraph2, paragraph3), testDoc.querySelector("*[class]"));

    }

    @Test
    public void testSelectAnyWithClassesAndId(){

        assertEquals(List.of(libForm, paragraph2), testDoc.querySelector("*[class][id]"));

    }

    @Test
    public void testSelectAnyWithClassesAndIdStartsWithMy(){

        assertEquals(List.of(libForm), testDoc.querySelector("*[class][id^=my]"));

    }

    @Test
    public void testSelectAnyWithClassesAndIdEndsWith2(){

        assertEquals(List.of(paragraph2), testDoc.querySelector("*[class][id$=2]"));

    }

    @Test
    public void testSelectParagraphWithClasses(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector("p[class]"));

    }

    @Test
    public void testClassAttributeInQuotes(){

        assertEquals(List.of(paragraph2, paragraph3), testDoc.querySelector("p[class=\"content spooky\"]"));

    }

}
