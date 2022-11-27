package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.EmptyForm;
import io.john.amiscaray.stir.stub.FormWithChildList;
import io.john.amiscaray.stir.stub.FormWithInputs;
import io.john.amiscaray.stir.stub.SimpleForm;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class ElementProcessorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    void isMyFormAForm() {

        assertEquals(processor.getTagName(EmptyForm.class), "form");

    }

    @Test
    void myFormMarkup() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/myFormMarkUpExpected.html") , processor.getMarkup(new EmptyForm()));

    }

    @Test
    void simpleFormWithAttributes() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/simpleFormWithAttributesExpected.html"), processor.getMarkup(new SimpleForm()));

    }

    @Test
    void formWithInputs() throws IOException {

        FormWithInputs form = new FormWithInputs();

        assertEquals(htmlLoader.getHTMLContentOf("html/formWithInputsExpected.html"), processor.getMarkup(form));

    }

    @Test
    void formWithChildList() throws IOException {

        Input username = new Input("text", "username", "John", null);
        Input message = new Input("text1", "message", "Some Message", null);
        FormWithChildList form = FormWithChildList.builder()
                .addInput(username)
                .addInput(message)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithChildListExpected.html"), processor.getMarkup(form));

    }

    @Test
    void testLibForm() throws IOException {

        Input username = new Input("text", "username", "John", null);
        Input message = new Input("text1", "message", "Some Message", null);
        Form form = Form.builder()
                        .addField(username)
                        .addField(message)
                        .action("/path")
                        .method("post")
                        .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/testLibFormExpected.html"), processor.getMarkup(form));

    }

    @Test
    void FormWithLabel() throws IOException {

        Input username = new Input("text", "username", "John", "Username");
        Form form = Form.builder()
                .addField(username)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithLabelExpected.html"), processor.getMarkup(form));

    }

}