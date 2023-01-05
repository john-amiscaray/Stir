package io.john.amiscaray.stir.tests;
import io.john.amiscaray.stir.domain.elements.Anchor;
import io.john.amiscaray.stir.domain.elements.Button;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.stub.ElementWithLongAndFloat;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.*;
import static org.junit.jupiter.api.Assertions.*;

public class ElementDescriptorProcessorTest {

    @Test
    public void testSimpleAnchorDescriptor() {

        assertEquals(new Anchor(), element("a"));

    }

    @Test
    public void testAnchorWithClasses() {

        assertEquals(Anchor.builder()
                .cssClass("red")
                .cssClass("blue")
                .cssClass("green")
                .build(), element("a.red.blue.green"));

    }

    @Test
    public void testAnchorWithClassesAndId() {

        assertEquals(Anchor.builder()
                .id("my-link")
                .cssClasses(List.of("red", "blue", "green"))
                .build(), element("a#my-link.red.blue.green"));

    }

    @Test
    public void testElementDescriptorWithAttributes() {

        assertEquals(Anchor.builder()
                .id("my-link")
                .href("https://google.com")
                .build(), element("a#my-link[href='https://google.com']"));

    }

    @Test
    public void testElementWithEmptyAttributeDescriptor() {

        assertEquals(Anchor.builder()
                .id("my-link")
                .build(), element("a#my-link[]"));

    }

    @Test
    public void testElementWithAttributeDescriptorMultiValues() {

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
    public void testElementWithAttributeSelectorValueHasBrackets() {

        assertEquals(Anchor.builder()
                .href("(){}[]")
                .build(), element("a[href='(){}[]']"));

    }

    // TODO expand testing for attribute selector to include values of all the supported types
    @Test
    public void testElementWithBooleanAttribute() {

        assertEquals(Button.builder()
                .autoFocus(true)
                .build(), element("button[autofocus='true']"));

    }

    @Test
    public void testElementWithDoubleAttribute() {

        assertEquals(Input.builder()
                .max(12.5)
                .build(), element("input[max='12.5']"));

    }

    @Test
    public void testElementWithIntegerAttribute() {

        assertEquals(Input.builder()
                .maxLength(3)
                .build(), element("input[maxlength='3']"));

    }

    @Test
    public void testElementWithLongAndFloatAttribute() {

        ElementWithLongAndFloat expected = ElementWithLongAndFloat.builder()
                .myFloat(12.3f)
                .myLong(999999999L)
                .build();
        ElementWithLongAndFloat actual = (ElementWithLongAndFloat) element("p[my-float='12.3',my-long='999999999']", "io.john.amiscaray.stir.stub");
        assertEquals(expected, actual);

    }

}
