package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.ListItem;
import io.john.amiscaray.stir.domain.elements.UnorderedList;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnorderedListTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor elementProcessor = ElementProcessor.getInstance();

    @Test
    public void testEmptyUnorderedList() throws IOException {

        UnorderedList ul = UnorderedList.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyUl.html"), elementProcessor.getMarkup(ul));

    }

    @Test
    public void testUnorderedListWithClassesAndId() throws IOException {

        UnorderedList ul = UnorderedList.builder()
                .id("myUl")
                .cssClass("red")
                .cssClass("blue")
                .cssClass("green")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/ulWithClassesAndId.html"), elementProcessor.getMarkup(ul));

    }

    @Test
    public void testUnorderedListWithListItems() throws IOException {

        UnorderedList ul = UnorderedList.builder()
                .listItem(new ListItem("Bacon"))
                .listItem(new ListItem("Tuna"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/ulWithListItems.html"), elementProcessor.getMarkup(ul));

    }

    @Test
    public void testAddListItemPropChangeSupport() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<List<ListItem>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<ListItem>> newFuture = new CompletableFuture<>();
        List<ListItem> children = new ArrayList<>(List.of(new ListItem("1"), new ListItem("2"), new ListItem("3")));
        UnorderedList.UnorderedListBuilder builder = UnorderedList.builder();
        for (ListItem child : children) {
            builder.listItem(child);
        }
        UnorderedList UnorderedList = builder.build();
        UnorderedList.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("listItems")){
                oldFuture.complete((List<ListItem>) prop.getOldValue());
                newFuture.complete((List<ListItem>) prop.getNewValue());
            }
        });

        ListItem newItem = new ListItem("4");
        UnorderedList.addListItem(newItem);

        List<ListItem> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<ListItem> n3w = newFuture.get(1, TimeUnit.SECONDS);
        assertEquals(children, old);
        children.add(newItem);
        assertEquals(children, n3w);

    }

    @Test
    public void testRemoveListItemPropChangeSupport() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<List<ListItem>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<ListItem>> newFuture = new CompletableFuture<>();

        ListItem i3 = new ListItem("newItem");
        List<ListItem> children = Arrays.asList(new ListItem("i1"), new ListItem("i2"), i3);
        UnorderedList.UnorderedListBuilder builder = UnorderedList.builder();
        for (ListItem child : children) {
            builder.listItem(child);
        }
        UnorderedList ul = builder.build();
        ul.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("listItems")){
                oldFuture.complete((List<ListItem>) prop.getOldValue());
                newFuture.complete((List<ListItem>) prop.getNewValue());
            }
        });

        ul.removeListItem(i3);
        List<ListItem> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<ListItem> n3w = newFuture.get(1, TimeUnit.SECONDS);
        assertEquals(children, old);
        assertEquals(children.stream()
                .filter(e -> !e.equals(i3))
                .collect(Collectors.toList()), n3w);

    }

    @Test
    public void testHiddenUL() throws IOException {

        UnorderedList ul = UnorderedList.builder()
                .style("color: red;")
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenUL.html"), elementProcessor.getMarkup(ul));

    }
    
}
