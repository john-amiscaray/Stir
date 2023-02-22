package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Section;
import io.john.amiscaray.stir.domain.elements.Table;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.Student;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testEmptySection() throws IOException {

        Section section = Section.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptySection.html"), processor.getMarkup(section));

    }

    @Test
    public void testSectionFromBuilderWithEverything() throws IOException {

        Section section = Section.builder()
                .id("mySection")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .style("color: red;")
                .hidden(true)
                .child(new Table(List.of(new Student(1, "John", 4.0f)), Student.class))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/sectionWithEverything.html"), processor.getMarkup(section));

    }

    @Test
    public void testSectionWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Section section = Section.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/sectionWithCustomAttributes.html"), processor.getMarkup(section));

    }

}
