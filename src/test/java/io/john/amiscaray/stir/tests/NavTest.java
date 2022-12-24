package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Nav;
import io.john.amiscaray.stir.domain.elements.NavLink;
import io.john.amiscaray.stir.domain.elements.NavLinkList;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class NavTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader loader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testBasicNav() throws IOException {


        LinkedHashMap<String, String> labelHrefMap = new LinkedHashMap<>();

        labelHrefMap.put("Home", "/home");
        labelHrefMap.put("Products", "/products");
        labelHrefMap.put("About Us", "/about");

        Nav nav = Nav.fromLabelHrefMap(labelHrefMap);

        assertEquals(loader.getHTMLContentOf("html/basicNav.html"), processor.getMarkup(nav));

    }

    @Test
    public void testNavLinkListBuilder() throws IOException {

        NavLinkList nll = NavLinkList.builder()
                .addNavLink(NavLink.fromLabelAndHref("Home", "/home"))
                .addNavLink(NavLink.fromLabelAndHref("Products", "/products"))
                .addNavLinkFromHrefAndLabel("About Us", "/about")
                .build();

        assertEquals(loader.getHTMLContentOf("html/basicLinkList.html"), processor.getMarkup(nll));

    }

}
