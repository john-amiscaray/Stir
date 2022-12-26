package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Div;
import io.john.amiscaray.stir.domain.elements.Footer;
import io.john.amiscaray.stir.domain.elements.Table;
import io.john.amiscaray.stir.stub.Student;
import org.junit.jupiter.api.Test;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FooterTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testEmptyFooter() throws IOException {

        Footer footer = Footer.fromDiv(Div.divBuilder()
                .build());

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyFooter.html"), processor.getMarkup(footer));

    }

    @Test
    public void testFooterWithFormChild() throws IOException {

        Footer footer = Footer.fromDiv(Div.divBuilder()
                        .child(new Table(List.of(new Student(1, "John", 4.0f)), Student.class))
                        .build());

        assertEquals(htmlLoader.getHTMLContentOf("html/footerWithFormChild.html"), processor.getMarkup(footer));

    }

    @Test
    public void testFooterFromDiv() throws IOException {

        Footer footer = Div.divBuilder()
                .child(new Table(List.of(new Student(1, "John", 4.0f)), Student.class))
                .build()
                .toFooter();

        assertEquals(htmlLoader.getHTMLContentOf("html/footerWithFormChild.html"), processor.getMarkup(footer));

    }

}
