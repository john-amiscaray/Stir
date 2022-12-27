package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Option;
import io.john.amiscaray.stir.domain.elements.Select;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testEmptySelect() throws IOException {

        Select select = Select.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptySelect.html"), processor.getMarkup(select));

    }

    @Test
    public void testSelectWithLabel() throws IOException {

        Select select = Select.builder()
                .label("Pick One:")
                .id("select")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/selectWithLabel.html"), processor.getMarkup(select));

    }

    @Test
    public void testSelectWithOptions() throws IOException {

        Select select = Select.builder()
                .option(new Option("One"))
                .option(new Option("Two"))
                .option(new Option("Three"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/selectWithOptions.html"), processor.getMarkup(select));

    }

    @Test
    public void testSelectWithClasses() throws IOException {

        Select select = Select.builder()
                .cssClass("red")
                .cssClass("blue")
                .cssClass("green")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/selectWithClasses.html"), processor.getMarkup(select));

    }

    @Test
    public void testSelectWithAllAttributes() throws IOException {

        Select select = Select.builder()
                .id("select")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .autoFocus(true)
                .disabled(true)
                .multiple(true)
                .name("color")
                .required(true)
                .form("myForm")
                .size(3)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/selectWithAll.html"), processor.getMarkup(select));

    }

}
