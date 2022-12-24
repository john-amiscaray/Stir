package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.StudentWithTableAnnotation;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DivTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testEmptyDiv() throws IOException {

        Div div = Div.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyDiv.html"), processor.getMarkup(div));

    }

    @Test
    public void testDivWithId() throws IOException {

        Div div = Div.builder()
                .id("myDiv")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/divWithId.html"), processor.getMarkup(div));

    }

    @Test
    public void testDivWithClass() throws IOException {

        Div div = Div.builder()
                .addClass("yellow")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/divWithClass.html"), processor.getMarkup(div));

    }

    @Test
    public void testDivWithMultipleClasses() throws IOException {

        Div div = Div.builder()
                .addClass("yellow")
                .addClass("red")
                .addClass("blue")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/multiClassDiv.html"), processor.getMarkup(div));

    }

    @Test
    public void testDivWithMultipleClassesAndId() throws IOException {

        Div div = Div.builder()
                .id("myDiv")
                .addClass("yellow")
                .addClass("red")
                .addClass("blue")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/divWithClassesAndId.html"), processor.getMarkup(div));

    }

    @Test
    public void testDivWithTableChild() throws IOException {

        List<StudentWithTableAnnotation> students = List.of(
                new StudentWithTableAnnotation(1, "Bobbert", 4.33f),
                new StudentWithTableAnnotation(2, "George", 3.33f),
                new StudentWithTableAnnotation(3, "Steve", 2.33f)
        );

        Div div = Div.builder()
                .addChild(processor.collectionToTableElement(students, StudentWithTableAnnotation.class))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/divWithTableChild.html"), processor.getMarkup(div));

    }

}
