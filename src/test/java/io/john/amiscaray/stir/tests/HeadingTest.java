package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.domain.elements.Heading;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HeadingTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testHeadings123() throws IOException {

        Div div = Div.builder()
                .child(new Heading(1, "This is a BIG plane"))
                .child(new Heading(2, "This is a smaller plane"))
                .child(new Heading(3, "This is an even smaller plane"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/testHeadings123.html"), processor.getMarkup(div));

    }

}
