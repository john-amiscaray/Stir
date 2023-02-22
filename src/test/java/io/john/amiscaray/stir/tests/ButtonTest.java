package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Button;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testButtonWithAll() throws IOException {

        Button button = Button.builder()
                .id("myBtn")
                .cssClasses(new ArrayList<>(List.of("btn", "btn-primary")))
                .cssClass("btn-important")
                .style("color: red;")
                .hidden(true)
                .disabled(true)
                .autoFocus(true)
                .form("myForm")
                .content("Press Me!")
                .value("btn")
                .formAction("/submit")
                .formMethod("get")
                .type("submit")
                .name("btn")
                .formEnctype("something")
                .formNoValidate(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/buttonWithAll.html"), processor.getMarkup(button));

    }

    @Test
    public void testButtonWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Button button = Button.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/buttonWithCustomAttributes.html"), processor.getMarkup(button));

    }

}
