package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Button;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testButtonWithAll() throws IOException {

        Button button = Button.builder()
                .id("myBtn")
                .cssClasses(new ArrayList<>(List.of("btn", "btn-primary")))
                .cssClass("btn-important")
                .disabled(true)
                .autoFocus(true)
                .form("myForm")
                .content("Press Me!")
                .value("btn")
                .formAction("/submit")
                .formMethod("get")
                .type("submit")
                .name("btn")
                .style("")
                .formEnctype("something")
                .formNoValidate(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/buttonWithAll.html"), processor.getMarkup(button));

    }

}
