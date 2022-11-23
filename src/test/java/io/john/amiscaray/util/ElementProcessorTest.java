package io.john.amiscaray.util;

import io.john.amiscaray.annotation.elements.Form;
import io.john.amiscaray.stub.MyForm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ElementProcessorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    void isMyFormAForm() {

        assertEquals(processor.getElementAnnotation(MyForm.class).annotationType(), Form.class);

    }

    @Test
    void myFormMarkup(){

        assertEquals( "<form></form>", processor.getMarkup(new MyForm()));

    }

}