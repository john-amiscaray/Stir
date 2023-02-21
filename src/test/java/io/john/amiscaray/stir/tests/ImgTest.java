package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Img;
import org.junit.jupiter.api.Test;

import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ImgTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testSimpleImg() throws IOException {

        Img i = new Img("A cool doggo", "./doggo.png");
        assertEquals(htmlLoader.getHTMLContentOf("html/simpleImg.html"), processor.getMarkup(i));

    }

    @Test
    public void testImgWithEverything() throws IOException {

        Img i = Img.builder()
                .id("aGoodBoy")
                .cssClasses(new ArrayList<>(List.of("cute", "pet")))
                .cssClass("photo")
                .alt("A cool doggo")
                .crossOrigin("anonymous")
                .height(200)
                .isMap(true)
                .loading("eager")
                .longDesc("/doggo")
                .referrerPolicy("origin")
                .sizes("something")
                .src("./doggo.png")
                .srcSet("something-else")
                .useMap("#some-map")
                .width(200)
                .style("border: 2px solid black;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/imgWithEverything.html"), processor.getMarkup(i));

    }

    @Test
    public void testImgWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Img img = Img.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/imgWithCustomAttributes.html"), processor.getMarkup(img));

    }

}
