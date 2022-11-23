package io.john.amiscaray.util;

import io.john.amiscaray.domain.elements.Input;
import io.john.amiscaray.stub.EmptyForm;
import io.john.amiscaray.stub.FormWithInputs;
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

    @Test
    void formWithInputs(){

        StringBuilder expected = new StringBuilder();
        FormWithInputs form = new FormWithInputs();
        Input username = form.getUsername();
        Input message = form.getMessage();
        expected.append("<form>\n")
                // Username input field
                .append("<input type=\"")
                .append(username.getType())
                .append("\" ")
                .append("id=\"")
                .append(username.getId())
                .append("\" ")
                .append("value=\"")
                .append(username.getValue())
                .append("\">\n")
                // Message input field
                .append("<input type=\"")
                .append(message.getType())
                .append("\" ")
                .append("id=\"")
                .append(message.getId())
                .append("\" ")
                .append("value=\"")
                .append(message.getValue())
                .append("\">\n")
                .append("\n</form>");
        assertEquals(expected.toString(), processor.getMarkup(form));

    }

}