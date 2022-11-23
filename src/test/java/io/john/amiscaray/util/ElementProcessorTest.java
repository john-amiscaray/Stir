package io.john.amiscaray.util;

import io.john.amiscaray.stub.EmptyForm;
import io.john.amiscaray.stub.SimpleForm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ElementProcessorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    void isMyFormAForm() {

        assertEquals(processor.getTagName(EmptyForm.class), "form");

    }

    @Test
    void myFormMarkup(){

        assertEquals( "<form>\n\n</form>", processor.getMarkup(new EmptyForm()));

    }

    @Test
    void simpleFormWithAttributes(){

        assertEquals("<form action=\"/\" method=\"post\">\n\n</form>", processor.getMarkup(new SimpleForm()));

    }

}