package io.john.amiscaray.util;

import io.john.amiscaray.annotation.Form;
import io.john.amiscaray.annotation.Input;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementProcessorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Test
    void isFormValid() {

        assertTrue(processor.isValidElementType(Form.class));

    }

    @Test
    void isInputValid(){

        assertTrue(processor.isValidElementType(Input.class));

    }

}