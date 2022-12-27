package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.IFrame;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IFrameTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testBasicIFrame() throws IOException {

        IFrame i = new IFrame("https://www.google.com");
        assertEquals(htmlLoader.getHTMLContentOf("html/basicIFrame.html"), processor.getMarkup(i));

    }

    @Test
    public void testIFrameWithAll() throws IOException {

        IFrame i = IFrame.builder()
                .id("iFrame")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .allow("geolocation")
                .allowFullScreen(true)
                .height(200)
                .loading("eager")
                .name("thing")
                .referrerPolicy("origin")
                .sandbox("allow-forms")
                .src("https://www.google.com")
                .srcDoc("something")
                .width(200)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/iFrameWithEverything.html"), processor.getMarkup(i));

    }

}
