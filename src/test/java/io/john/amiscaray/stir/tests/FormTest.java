package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.element;
import static org.junit.jupiter.api.Assertions.*;

public class FormTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testFormWithAll() throws IOException {

        Form form = Form.builder()
                .id("form")
                .cssClass("red")
                .cssClasses(List.of("blue", "green"))
                .field((Input) element("input[name='username',type='text']"))
                .fields(List.of((Input) element("input[name='password',type='password']"), (Input) element("input[name='amount',type='number']")))
                .method("post")
                .action("/login")
                .style("color: red;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/formWithAll.html"), processor.getMarkup(form));

    }

    @Test
    public void testFormWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Form form = Form.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/formWithCustomAttributes.html"), processor.getMarkup(form));

    }

}
