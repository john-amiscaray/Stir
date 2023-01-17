package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;

import java.io.IOException;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.element;
import static org.junit.jupiter.api.Assertions.*;

public class HiddenElementTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor elementProcessor = ElementProcessor.getInstance();

    @Test
    public void testHiddenParagraph() throws IOException {

        Paragraph p = (Paragraph) element("p('Hello World')");
        p.setHidden(true);
        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenParagraph.html"), elementProcessor.getMarkup(p));

    }

    @Test
    public void testHiddenParagraphWithStyles() throws IOException {

        Paragraph p = (Paragraph) element("p[style='color: red;']('Hello World')");
        p.setHidden(true);
        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenRedPara.html"), elementProcessor.getMarkup(p));

    }

    @Test
    public void testHiddenParagraphFromBuilder() throws IOException {

        Paragraph p = Paragraph.builder()
                .content("Hello World")
                .style("color: red;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenRedPara.html"), elementProcessor.getMarkup(p));

    }

}
