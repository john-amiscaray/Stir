package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.CssRule;
import io.john.amiscaray.stir.domain.elements.Style;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class StyleTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    public void testAddRawCSSPropSupport() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<StringBuilder> oldFuture = new CompletableFuture<>();
        CompletableFuture<StringBuilder> newFuture = new CompletableFuture<>();

        String initRules = "some rules";
        String moreRules = "more rules";

        Style style = new Style(initRules);

        style.addPropertyChangeListener(prop -> {

            if(prop.getPropertyName().equals("css")){
                oldFuture.complete((StringBuilder) prop.getOldValue());
                newFuture.complete((StringBuilder) prop.getNewValue());
            }

        });

        style.addStylesAsRawString(moreRules);

        StringBuilder old = oldFuture.get(1, TimeUnit.SECONDS);
        StringBuilder n3w = newFuture.get(1, TimeUnit.SECONDS);

        assertEquals(initRules, old.toString());
        assertEquals( initRules + "\n" + moreRules, n3w.toString());

    }

    @Test
    public void testAddRulePropSupport() throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<StringBuilder> oldFuture = new CompletableFuture<>();
        CompletableFuture<StringBuilder> newFuture = new CompletableFuture<>();

        String initRules = """
                nav {
                    background-color: yellow;
                }
                """;

        Style style = new Style(initRules);

        style.addPropertyChangeListener(prop -> {

            if(prop.getPropertyName().equals("css")){
                oldFuture.complete((StringBuilder) prop.getOldValue());
                newFuture.complete((StringBuilder) prop.getNewValue());
            }

        });

        CssRule rule = CssRule.builder()
                .selector("div")
                .style("color", "red")
                .build();

        style.addRule(rule);

        StringBuilder old = oldFuture.get(1, TimeUnit.SECONDS);
        StringBuilder n3w = newFuture.get(1, TimeUnit.SECONDS);

        assertEquals(initRules, old.toString());
        assertEquals( initRules + processor.processStyle(rule), n3w.toString());

    }

}
