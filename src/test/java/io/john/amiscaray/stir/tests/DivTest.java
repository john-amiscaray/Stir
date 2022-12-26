package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.domain.elements.Paragraph;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.StudentWithTableAnnotation;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

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

    @Test
    public void testDivPropertyChangeOnChildAdd() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<List<AbstractUIElement>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<AbstractUIElement>> newFuture = new CompletableFuture<>();
        List<AbstractUIElement> children = new ArrayList<>(List.of(new Paragraph("1"), new Paragraph("2"), new Paragraph("3")));
        Div.Builder builder = Div.builder();
        for (AbstractUIElement child : children) {
            builder.addChild(child);
        }
        Div div = builder.build();
        div.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("children")){
                oldFuture.complete((List<AbstractUIElement>) prop.getOldValue());
                newFuture.complete((List<AbstractUIElement>) prop.getNewValue());
            }
        });

        Paragraph newPara = new Paragraph("4");
        div.addChild(newPara);

        List<AbstractUIElement> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<AbstractUIElement> n3w = newFuture.get(1, TimeUnit.SECONDS);
        assertEquals(children, old);
        children.add(newPara);
        assertEquals(children, n3w);

    }

    @Test
    public void testDivPropertyChangeOnChildRemove() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<List<AbstractUIElement>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<AbstractUIElement>> newFuture = new CompletableFuture<>();

        Paragraph p3 = new Paragraph("3");
        List<AbstractUIElement> children = new ArrayList<>(List.of(new Paragraph("1"), new Paragraph("2"), p3));
        Div.Builder builder = Div.builder();
        for (AbstractUIElement child : children) {
            builder.addChild(child);
        }
        Div div = builder.build();
        div.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("children")){
                oldFuture.complete((List<AbstractUIElement>) prop.getOldValue());
                newFuture.complete((List<AbstractUIElement>) prop.getNewValue());
            }
        });

        div.removeChild(p3);
        List<AbstractUIElement> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<AbstractUIElement> n3w = newFuture.get(1, TimeUnit.SECONDS);
        assertEquals(children, old);
        assertEquals(children.stream()
                        .filter(e -> !e.equals(p3))
                        .collect(Collectors.toList()), n3w);

    }

}
