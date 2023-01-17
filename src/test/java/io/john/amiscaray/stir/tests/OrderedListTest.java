package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.ListItem;
import io.john.amiscaray.stir.domain.elements.OrderedList;
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

import static org.junit.jupiter.api.Assertions.*;

public class OrderedListTest {

    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final ElementProcessor elementProcessor = ElementProcessor.getInstance();

    @Test
    public void testEmptyOrderedList() throws IOException {

        OrderedList ol = OrderedList.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyOl.html"), elementProcessor.getMarkup(ol));

    }

    @Test
    public void testOrderedListWithClassesAndId() throws IOException {

        OrderedList ol = OrderedList.builder()
                .id("myOl")
                .cssClass("red")
                .cssClass("blue")
                .cssClass("green")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/olWithClassesAndId.html"), elementProcessor.getMarkup(ol));

    }

    @Test
    public void testOrderedListWithListItems() throws IOException {

        OrderedList ol = OrderedList.builder()
                .listItem(new ListItem("Bacon"))
                .listItem(new ListItem("Tuna"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/olWithListItems.html"), elementProcessor.getMarkup(ol));

    }
    
    @Test
    public void testAddListItemPropChangeSupport() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<List<ListItem>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<ListItem>> newFuture = new CompletableFuture<>();
        List<ListItem> children = new ArrayList<>(List.of(new ListItem("1"), new ListItem("2"), new ListItem("3")));
        OrderedList.OrderedListBuilder builder = OrderedList.builder();
        for (ListItem child : children) {
            builder.listItem(child);
        }
        OrderedList orderedList = builder.build();
        orderedList.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("listItems")){
                oldFuture.complete((List<ListItem>) prop.getOldValue());
                newFuture.complete((List<ListItem>) prop.getNewValue());
            }
        });

        ListItem newItem = new ListItem("4");
        orderedList.addListItem(newItem);

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
        OrderedList.OrderedListBuilder builder = OrderedList.builder();
        for (ListItem child : children) {
            builder.listItem(child);
        }
        OrderedList ol = builder.build();
        ol.addPropertyChangeListener(prop -> {
            if(prop.getPropertyName().equals("listItems")){
                oldFuture.complete((List<ListItem>) prop.getOldValue());
                newFuture.complete((List<ListItem>) prop.getNewValue());
            }
        });

        ol.removeListItem(i3);
        List<ListItem> old = oldFuture.get(1, TimeUnit.SECONDS);
        List<ListItem> n3w = newFuture.get(1, TimeUnit.SECONDS);
        assertEquals(children, old);
        assertEquals(children.stream()
                .filter(e -> !e.equals(i3))
                .collect(Collectors.toList()), n3w);

    }

    @Test
    public void testHiddenOL() throws IOException {

        OrderedList ol = OrderedList.builder()
                .hidden(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/hiddenOL.html"), elementProcessor.getMarkup(ol));

    }

}