package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Anchor;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AnchorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testAnchorWithAll() throws IOException {

        Anchor a = Anchor.builder()
                .id("link")
                .cssClass("link")
                .cssClasses(List.of("red", "blue", "green"))
                .style("color: red;")
                .hidden(true)
                .href("https://google.com")
                .label("Google")
                .download("something")
                .hrefLang("en")
                .target("idk")
                .media("idk")
                .ping("idk")
                .referrerPolicy("idk")
                .rel("idk")
                .type("idk")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/anchorWithAll.html"), processor.getMarkup(a));

    }

    @Test
    public void testAnchorWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");
        Anchor a = Anchor.builder()
                .customAttribute("data-info", "hello-world")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/anchorWithCustomAttributes.html"), processor.getMarkup(a));

    }

}
