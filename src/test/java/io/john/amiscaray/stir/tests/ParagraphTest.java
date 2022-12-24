package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.CacheableElement;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.domain.elements.Paragraph;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class ParagraphTest {

    private final ExpectedHTMLLoader loader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testSimpleParagraph() throws IOException {

        Paragraph p = new Paragraph("Hello World");
        assertEquals(loader.getHTMLContentOf("html/simpleParagraph.html"), processor.getMarkup(p));

    }

    @Test
    public void testParagraphWithClasses() throws IOException {

        Paragraph p = Paragraph.builder()
                .addClass("yellow")
                .addClass("red")
                .build();

        assertEquals(loader.getHTMLContentOf("html/pWithClasses.html"), processor.getMarkup(p));

    }

    @Test
    public void testParagraphWithClassesAndId() throws IOException {

        Paragraph p = Paragraph.builder()
                .addClass("yellow")
                .addClass("red")
                .id("myP")
                .build();

        assertEquals(loader.getHTMLContentOf("html/pWithClassesAndId.html"), processor.getMarkup(p));

    }

    @Test
    public void testParagraphChangeSupport() throws ExecutionException, InterruptedException, TimeoutException {

        Paragraph p = new Paragraph("Hello World");
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        p.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("content")){
                future.complete(List.of((String) prop.getOldValue(), (String) prop.getNewValue()));
            }
        });
        p.setContent("No");

        List<String> result = future.get(1, TimeUnit.SECONDS);
        assertEquals( "Hello World", result.get(0));
        assertEquals( "No", result.get(1));

    }

    @Test
    public void testParagraphCache() {

        Paragraph p = new Paragraph("This is text");
        String freshMarkup = processor.getMarkup(p);
        String cachedMarkup = processor.getMarkup(p);

        assertEquals(freshMarkup, cachedMarkup);

    }

    @Test
    public void testParagraphCacheStatus() {

        Paragraph p = Paragraph.builder()
                .content("This is text")
                .addClass("clazz")
                .id("id")
                .addClass("text")
                .build();
        assertEquals(CacheableElement.CacheStatus.EMPTY, p.getCacheStatus());
        processor.getMarkup(p);
        assertEquals(CacheableElement.CacheStatus.CLEAN, p.getCacheStatus());
        p.setContent("This is not text");
        assertEquals(CacheableElement.CacheStatus.DIRTY, p.getCacheStatus());

    }

}
