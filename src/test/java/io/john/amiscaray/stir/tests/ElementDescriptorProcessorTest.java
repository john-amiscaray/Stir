package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.domain.elements.exceptions.ElementInitializationException;
import io.john.amiscaray.stir.stub.ElementWithLongAndFloat;
import io.john.amiscaray.stir.stub.ListStub;
import io.john.amiscaray.stir.util.ElementDescriptorProcessor;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.*;
import static org.junit.jupiter.api.Assertions.*;

public class ElementDescriptorProcessorTest {

    @AfterEach
    public void cleanUpAfterEach() {

        ElementDescriptorProcessor.setBasePackage(DEFAULT_BASE_PACKAGE);

    }

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

    @Test
    public void testElementWithAttributeSelectorKeyWithWhitespace() {

        assertThrows(DescriptorFormatException.class, () -> element("a[fake key='some value']"));

    }

    @Test
    public void testMisplacedFieldDescriptors() {

        assertThrows(DescriptorFormatException.class, () -> element("a()[]{}"));
        assertThrows(DescriptorFormatException.class, () -> element("a(){}[]"));

    }

    @Test
    public void testElementWithInnerContent() {

        assertEquals(Anchor.builder().label("Hello World").build(), element("a('Hello World')"));

    }

    @Test
    public void testElementWithInnerContentAndAttribute() {

        assertEquals(Anchor.builder().href("https://google.com").label("Google").build(), element("a[href='https://google.com']('Google')"));

    }

    @Test
    public void testElementWithInnerContentDescriptorWithNestedBrackets() {

        assertEquals(Anchor.builder().label("{[(").build(), element("a('{[(')"));

    }

    @Test
    public void testElementWithAttributeDescriptorAndInnerContentDescriptorWithNestedBrackets() {

        assertEquals(Anchor.builder().label("{[(").href("https://google.com").build(), element("a[href='https://google.com']('{[(')"));

    }

    @Test
    public void testElementWithParagraphChild() {

        assertEquals(Div.builder()
                .child(new Paragraph("Hello World"))
                .build(), element("div{p('Hello World')}"));

    }

    @Test
    public void testDivWithMultipleParagraphChildren() {

        assertEquals(Div.builder()
                .child(new Paragraph("Hello World"))
                .child(new Paragraph("This is content"))
                .build(), element("div{p('Hello World'),p('This is content')}"));

    }

    @Test
    public void testFormWithInputChild() {

        assertEquals(Form.builder()
                .field(Input.builder()
                        .type("text")
                        .build())
                .build(), element("form{input[type='text']}"));

    }

    @Test
    public void testFormWithChildrenOfWrongType() {

        assertThrows(DescriptorFormatException.class, () -> element("form{p('Hello World')}"));

    }

    @Test
    public void testDivWithIDClassesAttributesAndChildren() {

        assertEquals(Form.builder()
                .cssClasses(new ArrayList<>(List.of("red", "blue", "green")))
                .action("/login")
                .method("post")
                .id("my-form")
                .field(Input.builder()
                        .type("text")
                        .build())
                .field(Input.builder()
                        .type("password")
                        .build())
                .build(), element("form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}"));

    }

    @Test
    public void testInnerContentDescriptorWithDescriptorAsInnerContent() {

        assertEquals(new Paragraph("form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}"),
                element("p('form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}')"));

    }

    @Test
    public void testClassWithWhitespace() {

        assertThrows(DescriptorFormatException.class, () -> element("p.hello world"));

    }

    @Test
    public void testTwoDescriptorsInARow() {

        assertThrows(DescriptorFormatException.class, () -> element("form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}"));

    }

    @Test
    public void testAttributeWithWhitespace() {

        assertThrows(DescriptorFormatException.class, () -> element("form[action thing='']"));

    }

    @Test
    public void testSetBasePackage() {

        ElementDescriptorProcessor.setBasePackage("io.john.amiscaray.stir.stub");
        assertTrue(element("ul") instanceof ListStub);

    }

    @Test
    public void testMultipleIDs() {

        assertThrows(DescriptorFormatException.class, () -> element("div#container#my-div"));

    }

    @Test
    public void testElementWithBadAttribute() {

        ElementDescriptorProcessor.setBasePackage("io.john.amiscaray.stir.stub");
        assertThrows(ElementInitializationException.class, () -> element("div[thing='aaaaaa']"));

    }

    @Test
    public void testAttributeDescriptorWithInvalidBooleanValid() {

        assertThrows(DescriptorFormatException.class, () -> element("button[formnovalidate='aaaaaaa']"));

    }

}
