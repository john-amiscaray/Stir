package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.LinkedStyle;
import io.john.amiscaray.stir.domain.elements.Style;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedStyleTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testLinkedStyleWithAll() throws IOException {

        LinkedStyle style = LinkedStyle.builder()
                .href("#")
                .crossOrigin("*")
                .integrity("idk")
                .cssClass("red")
                .cssClasses(List.of("blue", "green"))
                .style("color: red;")
                .id("style")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/styleWithAll.html"), processor.getMarkup(style));

    }

    @Test
    public void testLinkedStyleWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        LinkedStyle linkedStyle = LinkedStyle.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/linkedStyleWithCustomAttributes.html"), processor.getMarkup(linkedStyle));

    }

}
