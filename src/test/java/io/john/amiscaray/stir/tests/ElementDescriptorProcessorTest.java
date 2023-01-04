package io.john.amiscaray.stir.tests;
import io.john.amiscaray.stir.domain.elements.Anchor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.*;
import static org.junit.jupiter.api.Assertions.*;

public class ElementDescriptorProcessorTest {

    @Test
    public void testSimpleAnchorDescriptor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(new Anchor(), element("a"));

    }

    @Test
    public void testAnchorWithClasses() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .cssClass("red")
                .cssClass("blue")
                .cssClass("green")
                .build(), element("a.red.blue.green"));

    }

    @Test
    public void testAnchorWithClassesAndId() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .id("my-link")
                .cssClasses(List.of("red", "blue", "green"))
                .build(), element("a#my-link.red.blue.green"));

    }

}
