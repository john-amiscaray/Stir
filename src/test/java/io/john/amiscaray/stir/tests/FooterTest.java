package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Footer;
import io.john.amiscaray.stir.domain.elements.Table;
import io.john.amiscaray.stir.stub.Student;
import org.junit.jupiter.api.Test;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FooterTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testEmptyFooter() throws IOException {

        Footer footer = Footer.builder()
                        .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyFooter.html"), processor.getMarkup(footer));

    }

    @Test
    public void testFooterWithIdAndClasses() throws IOException {

        Footer footer = Footer.builder()
                .id("myFooter")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/footerWithIdAndClasses.html"), processor.getMarkup(footer));

    }

    @Test
    public void testFooterWithFormChild() throws IOException {

        Footer footer = Footer.builder()
                        .child(new Table(List.of(new Student(1, "John", 4.0f)), Student.class))
                        .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/footerWithTableChild.html"), processor.getMarkup(footer));

    }

    @Test
    public void testHiddenFooter() throws IOException {

        Footer footer = Footer.builder()
                .style("color: red;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenFooter.html"), processor.getMarkup(footer));

    }

    @Test
    public void testFooterWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Footer footer = Footer.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/footerWithCustomAttributes.html"), processor.getMarkup(footer));

    }

}
