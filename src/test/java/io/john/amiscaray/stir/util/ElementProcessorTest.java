package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.domain.elements.Script;
import io.john.amiscaray.stir.domain.elements.Style;
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
    private final Input username = new Input("text", "text", "John", null);
    private final Input message = new Input("text1", "text", "Some Message", null);
    private final Form sampleLibForm = Form.builder()
            .addField(username)
            .addField(message)
            .action("/path")
            .method("post")
            .build();

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

        FormWithChildList form = FormWithChildList.builder()
                .addInput(username)
                .addInput(message)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithChildListExpected.html"), processor.getMarkup(form));

    }

    @Test
    void testLibForm() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/testLibFormExpected.html"), processor.getMarkup(sampleLibForm));

    }

    @Test
    void testLibFormAsDocWithStyles() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                        .addElement(sampleLibForm)
                        .addStyle(new Style(
                                "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css",
                                "sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65",
                                "anonymous"
                        ))
                        .addStyle(new Style("./styles.css"))
                        .title("Hello")
                        .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/testLibFormAsDocExpected.html"), doc.generateDocumentString());

    }

    @Test
    void testLibFormAsDocWithFooterScript() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .addElement(sampleLibForm)
                .addScript(new Script("./main.js"), HTMLDocument.DocumentPosition.FOOTER)
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/testDocWithFooterScriptExpected.html"), doc.generateDocumentString());

    }

    @Test
    void FormWithLabel() throws IOException {

        Input username = new Input("text", "text", "John", "Username");
        Form form = Form.builder()
                .addField(username)
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithLabelExpected.html"), processor.getMarkup(form));

    }

}