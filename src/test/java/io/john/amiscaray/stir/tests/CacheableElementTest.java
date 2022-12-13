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
    public void testAddClassListenerGivesCorrectOldList() throws ExecutionException, InterruptedException {

        List<String> classes = new ArrayList<>(Arrays.asList("my-class"));
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        in.setClassList(classes);
        in.addPropertyChangeListener(e -> {
            if(e.getPropertyName().equals("classList")){
                future.complete((List<String>) e.getOldValue());
            }
        });
        in.addClass("another-class");
        assertEquals(List.of("my-class"), future.get());

    }

}
