package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.HGroup;
import io.john.amiscaray.stir.domain.elements.Heading;
import io.john.amiscaray.stir.domain.elements.Paragraph;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HGroupTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testHGroupWithEverything() throws IOException {

        HGroup hGroup = HGroup.builder()
                .id("main-title")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .child(new Heading(1, "This is a BIG title"))
                .child(new Heading(2, "This is a smaller title"))
                .child(new Paragraph("This is some text"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hGroupWithEverything.html"), processor.getMarkup(hGroup));

    }


}
