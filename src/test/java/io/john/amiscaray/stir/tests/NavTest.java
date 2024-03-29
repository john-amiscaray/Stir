package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Anchor;
import io.john.amiscaray.stir.domain.elements.Nav;
import io.john.amiscaray.stir.domain.elements.NavLink;
import io.john.amiscaray.stir.domain.elements.NavLinkList;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
                .navLink(NavLink.fromLabelAndHref("Home", "/home"))
                .navLink(NavLink.fromLabelAndHref("Products", "/products"))
                .navLink(NavLink.fromLabelAndHref("About Us", "/about"))
                .build();

        assertEquals(loader.getHTMLContentOf("html/basicLinkList.html"), processor.getMarkup(nll));

    }

    @Test
    public void testNavLinkListChange() throws ExecutionException, InterruptedException, TimeoutException {

        List<NavLink> links = new ArrayList<>();
        links.add(NavLink.fromLabelAndHref("home", "/home"));
        CompletableFuture<List<NavLink>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<NavLink>> newFuture = new CompletableFuture<>();
        NavLinkList linkList = new NavLinkList(links);
        linkList.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("navLinks")){
                oldFuture.complete((List<NavLink>) prop.getOldValue());
                newFuture.complete((List<NavLink>) prop.getNewValue());
            }
        });
        NavLink newLink = NavLink.fromLabelAndHref("about", "/about");
        linkList.addNavLink(newLink);
        List<NavLink> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<NavLink> n3w = newFuture.get(1, TimeUnit.SECONDS);

        assertEquals(old.size(), 1);
        assertEquals(old.get(0), links.get(0));
        assertEquals(n3w.size(), 2);
        assertEquals(n3w.get(0), links.get(0));
        assertEquals(n3w.get(1), newLink);

    }

    @Test
    public void testHiddenNavWithClassesIdAndStyle() throws IOException {

        Nav nav = Nav.builder()
                .id("nav")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .style("color: red;")
                .hidden(true)
                .list(NavLinkList.builder()
                        .id("nav-list")
                        .cssClass("red")
                        .cssClasses(new ArrayList<>(List.of("blue", "green")))
                        .navLink(NavLink.builder()
                                .id("nav-link")
                                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                                .cssClass("green")
                                .a(Anchor.builder()
                                        .id("a")
                                        .label("Home")
                                        .href("/home")
                                        .cssClasses(new ArrayList<>(List.of("red", "blue")))
                                        .cssClass("green")
                                        .build())
                                .build())
                        .build())
                .build();

        assertEquals(loader.getHTMLContentOf("html/navWithClasses.html"), processor.getMarkup(nav));

    }

    @Test
    public void testHiddenNavLinkList() throws IOException {

        NavLinkList nll = NavLinkList.builder()
                .hidden(true)
                .build();

        assertEquals(loader.getHTMLContentOf("html/hiddenNavLinkList.html"), processor.getMarkup(nll));

    }

    @Test
    public void testHiddenNavLink() throws IOException {

        NavLink nl = NavLink.builder()
                .hidden(true)
                .build();

        assertEquals(loader.getHTMLContentOf("html/hiddenNavLink.html"), processor.getMarkup(nl));

    }

    @Test
    public void testNavWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        Nav nav = Nav.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(loader.getHTMLContentOf("html/navWithCustomAttributes.html"), processor.getMarkup(nav));

    }

    @Test
    public void testNavLinkListWithCustomAttributes() throws IOException {

        LinkedHashMap<String, String> attributes = new LinkedHashMap<>();
        attributes.put("data-todo-id", "1");
        attributes.put("data-role", "link");

        NavLinkList nll = NavLinkList.builder()
                .customAttribute("data-color", "red")
                .customAttributes(attributes)
                .build();

        assertEquals(loader.getHTMLContentOf("html/navLinkListWithCustomAttributes.html"), processor.getMarkup(nll));

    }

}
