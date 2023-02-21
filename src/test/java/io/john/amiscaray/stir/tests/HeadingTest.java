package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.domain.elements.Heading;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class HeadingTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testHeadings123() throws IOException {

        Div div = Div.builder()
                .child(new Heading(1, "This is a BIG plane"))
                .child(Heading.builder()
                        .level(2)
                        .content("This is a smaller plane")
                        .build())
                .child(Heading.builder()
                        .level(3)
                        .content("This is an even smaller plane")
                        .build())
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/testHeadings123.html"), processor.getMarkup(div));

    }

    @Test
    public void testHiddenHeading() throws IOException {

        Heading heading = Heading.builder()
                .level(1)
                .style("color: red;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenHeading.html"), processor.getMarkup(heading));

    }

    @Test
    public void testHeadingWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Heading heading = Heading.builder()
                .level(1)
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/headingWithCustomAttributes.html"), processor.getMarkup(heading));

    }

    // TODO fix this not working
    @Test
    @Disabled
    public void testHeadingWithNoLevelSet() throws IOException {

        Heading heading = Heading.builder()
                .content("This is the title")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/headingWithNoLevelSet.html"), processor.getMarkup(heading));

    }

}
