package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Header;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.element;
import static org.junit.jupiter.api.Assertions.*;

public class HeaderTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testHeaderWithAll() throws IOException {

        Header header = Header.builder()
                .id("my-header")
                .cssClasses(List.of("red", "blue"))
                .cssClass("green")
                .style("color: red;")
                .hidden(true)
                .children(List.of(element("h1('This is a thing')"), element("h2('AAAA')")))
                .child(element("h3('Hello World')"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/headerWithAll.html"), processor.getMarkup(header));

    }

}
