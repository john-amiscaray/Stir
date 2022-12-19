package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.stub.FormWithInputs;
import io.john.amiscaray.stir.stub.Paragraph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueryTest {

    private final Paragraph myParagraph = new Paragraph("Hello World!");

    private final HTMLDocument testDoc = HTMLDocument.builder()
            .addElement(new FormWithInputs())
            .addElement(myParagraph)
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

}
