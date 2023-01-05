package io.john.amiscaray.stir.tests;
import io.john.amiscaray.stir.domain.elements.Anchor;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
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

    @Test
    public void testElementDescriptorWithAttributes() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .id("my-link")
                .href("https://google.com")
                .build(), element("a#my-link[href='https://google.com']"));

    }

    @Test
    public void testElementWithEmptyAttributeDescriptor() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .id("my-link")
                .build(), element("a#my-link[]"));

    }

    @Test
    public void testElementWithAttributeDescriptorMultiValues() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .id("my-link")
                .download("/files/doggo.png")
                .href("https://google.com")
                .build(), element("a#my-link[download='/files/doggo.png',href='https://google.com']"));

    }

    @Test
    public void testElementWithAttributeSelectorValueNotInQuotes() {

        assertThrows(DescriptorFormatException.class, () -> element("a[href=https://google.com]"));

    }

    @Test
    public void testElementWithAttributeSelectorValueUnclosedQuotes() {

        assertThrows(DescriptorFormatException.class, () -> element("a[href='https://google.com]"));

    }

    @Test
    public void testElementWithIncorrectQuotesInAttributeSelector() {

        assertThrows(DescriptorFormatException.class, () -> element("a[href=\"https://google.com\"]"));

    }

    @Test
    public void testElementWithAttributeSelectorWithNestedBrackets() {

        assertThrows(DescriptorFormatException.class, () -> element("a[href(='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href)='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href()='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href{='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href}='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href{}='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href[='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href]='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href[]='']"));
        assertThrows(DescriptorFormatException.class, () -> element("a[href(){}[]='']"));

    }

    @Test
    public void testElementWithAttributeSelectorValueHasBrackets() throws InvocationTargetException, InstantiationException, IllegalAccessException {

        assertEquals(Anchor.builder()
                .href("(){}[]")
                .build(), element("a[href='(){}[]']"));

    }

    // TODO expand testing for attribute selector to include values of all the supported types

}