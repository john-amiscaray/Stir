package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class ElementProcessorTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final Input username = new Input("text", "text", "John", null);
    private final Input message = new Input("text1", "text", "Some Message", null);
    private final Form sampleLibForm = Form.builder()
            .addField(username)
            .addField(message)
            .action("/path")
            .method("post")
            .build();

    @Test
    public void testMyFormAForm() {

        assertEquals(processor.getTagName(EmptyForm.class), "form");

    }

    @Test
    public void testMyFormMarkup() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/myFormMarkUpExpected.html") , processor.getMarkup(new EmptyForm()));

    }

    @Test
    public void testSimpleFormWithAttributes() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/simpleFormWithAttributesExpected.html"), processor.getMarkup(new SimpleForm()));

    }

    @Test
    public void testFormWithInputs() throws IOException {

        FormWithInputs form = new FormWithInputs();

        assertEquals(htmlLoader.getHTMLContentOf("html/formWithInputsExpected.html"), processor.getMarkup(form));

    }

    @Test
    public void testFormWithChildList() throws IOException {

        FormWithChildList form = FormWithChildList.builder()
                .addInput(username)
                .addInput(message)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithChildListExpected.html"), processor.getMarkup(form));

    }

    @Test
    public void testLibForm() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/libFormExpected.html"), processor.getMarkup(sampleLibForm));

    }

    @Test
    public void testFormWithLabel() throws IOException {

        Input username = new Input("text", "text", "John", "Username");
        Form form = Form.builder()
                .addField(username)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithLabelExpected.html"), processor.getMarkup(form));

    }

    @Test
    public void testParagraphWithInnerContent() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/paragraphWithInnerContentExpected.html"), processor.getMarkup(new Paragraph("This is a paragraph")));

    }

}