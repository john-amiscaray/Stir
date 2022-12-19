package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.stub.FormWithInputs;
import io.john.amiscaray.stir.stub.Paragraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private final Paragraph myParagraph = new Paragraph("Hello World!");
    private final Form libForm = Form.builder()
            .id("myForm")
            .addClass("form")
            .build();

    private final HTMLDocument testDoc = HTMLDocument.builder()
            .addElement(new FormWithInputs())
            .addElement(myParagraph)
            .addElement(libForm)
            .build();

    @Test
    public void testGroupQueryIsFlattenedList(){

        testDoc.querySelector("*, *").forEach(
                element -> assertFalse(element instanceof List)
        );

    }

    @Test
    public void testFindAllParagraphs(){

        assertEquals(testDoc.querySelector("p"), List.of(myParagraph));

    }

    @Test
    public void testQueryById(){

        assertEquals(testDoc.querySelector("#myForm"), List.of(libForm));

    }

    @Test
    public void testQueryByClass(){

        assertEquals(testDoc.querySelector(".form"), List.of(libForm));

    }

}
