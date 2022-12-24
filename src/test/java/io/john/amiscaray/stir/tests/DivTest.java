package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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

}
