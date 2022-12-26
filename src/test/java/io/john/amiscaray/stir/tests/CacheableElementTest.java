package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.CacheableElement.CacheStatus;
import io.john.amiscaray.stir.domain.elements.Input;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class CacheableElementTest {

    private final Input in = Input.builder()
            .label("Hello")
            .value("World")
            .type("text")
            .build();

    @AfterEach
    void cleanUpEach(){

        in.emptyCache();

    }

    @Test
    public void testCacheEmptyWhenCleared(){

        assertEquals(in.getCacheStatus(), CacheStatus.EMPTY);

    }

    @Test
    public void testInputCleanAfterCacheSet(){

        in.setCacheContents("Dummy Text");
        assertEquals(in.getCacheStatus(), CacheStatus.CLEAN);

    }

    @Test
    public void testInputDirtyAfterValueChange(){

        in.setCacheContents("Dummy Text");
        in.setValue("New value");
        assertEquals(CacheStatus.DIRTY, in.getCacheStatus());

    }

    @Test
    public void testInputDirtyAfterAddClass(){

        in.setCacheContents("Dummy Text");
        in.addClass("my-class");
        assertEquals(CacheStatus.DIRTY, in.getCacheStatus());

    }

    @Test
    public void testAddClassListenerGivesCorrectOldList() throws ExecutionException, InterruptedException, TimeoutException {

        List<String> classes = new ArrayList<>(Arrays.asList("my-class"));
        CompletableFuture<List<String>> oldFuture = new CompletableFuture<>();
        CompletableFuture<List<String>> newFuture = new CompletableFuture<>();
        in.setClasses(classes);
        in.addPropertyChangeListener(e -> {
            if(e.getPropertyName().equals("classList")){
                oldFuture.complete((List<String>) e.getOldValue());
                newFuture.complete((List<String>) e.getNewValue());
            }
        });
        in.addClass("another-class");
        assertEquals(List.of("my-class"), oldFuture.get(1, TimeUnit.SECONDS));
        assertEquals(List.of("my-class", "another-class"), newFuture.get(1, TimeUnit.SECONDS));

    }

}
