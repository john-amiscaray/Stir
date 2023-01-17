package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Table;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.Student;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testStudentTableFromBuilder() throws IOException {

        Table table = Table.builder()
                .clazz(Student.class)
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .style("color: red;")
                .hidden(true)
                .id("table")
                .entries(new ArrayList<>(List.of(new Student(1, "John", 0.5f), new Student(2, "Jill", 3.1f))))
                .entry(new Student(3, "David", 2.2f))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/studentFromBuilder.html"), processor.getMarkup(table));

    }

}
