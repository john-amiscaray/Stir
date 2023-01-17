package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Option;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class OptionTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testOptionFromBuilder() throws IOException {

        Option option = Option.builder()
                .value("red")
                .optionName("Red")
                .id("red")
                .cssClass("color")
                .style("color: red;")
                .hidden(true)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/optionTest.html"), processor.getMarkup(option));

    }

}
