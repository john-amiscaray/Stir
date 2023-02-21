package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Input;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testInputWithParentFieldsAndCommonDeclaredFields() throws IOException {

        Input in = Input.builder()
                .id("my-in")
                .cssClasses(List.of("red", "blue"))
                .cssClass("green")
                .type("text")
                .name("username")
                .style("color: red;")
                .hidden(true)
                .autoFocus(true)
                .disabled(true)
                .form("my-form")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/inputTest.html"), processor.getMarkup(in));

    }

    @Test
    public void testInputFrom5ArgConstructor() throws IOException {

        Input in = new Input("my-in", "text", "Hello", "This is a thing", "myIn");
        assertEquals(htmlLoader.getHTMLContentOf("html/inputTest2.html"), processor.getMarkup(in));

    }

    @Test
    public void testInputFrom3ArgConstructor() throws IOException {

        Input in = new Input("text", "text", "myIn");
        assertEquals(htmlLoader.getHTMLContentOf("html/inputTest3.html"), processor.getMarkup(in));

    }

    @Test
    public void testInputWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Input input = Input.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/inputWithCustomAttributes.html"), processor.getMarkup(input));

    }

}
