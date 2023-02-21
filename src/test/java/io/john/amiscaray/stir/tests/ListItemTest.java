package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.ListItem;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ListItemTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testListItemFromBuilder() throws IOException {

        ListItem li = ListItem.builder()
                .id("someItem")
                .cssClass("someClass")
                .style("color: red;")
                .hidden(true)
                .content("Some Text")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/listItemTest.html"), processor.getMarkup(li));

    }

    @Test
    public void testListItemWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        ListItem li = ListItem.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/listItemWithCustomAttributes.html"), processor.getMarkup(li));

    }

}
