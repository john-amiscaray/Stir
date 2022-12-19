package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Form;
import io.john.amiscaray.stir.domain.elements.Input;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.*;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ElementProcessorTest {

    public static final String ALL_FORMATS = "%a%b%c%d%e%f%g%h%n%o%s%t%x";
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

    @AfterEach
    public void tearDown(){

        sampleLibForm.emptyCache();
        username.emptyCache();
        message.emptyCache();

    }

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

    @Test
    public void testProcessorWritesInputToCache(){

        String content = processor.getMarkup(username);
        assertEquals(content, username.getCacheContents());

    }

    @Test
    public void testProcessorInputCache(){

        String computedMarkup = processor.getMarkup(username);
        String markupFromCache = processor.getMarkup(username);
        assertEquals(computedMarkup, markupFromCache);

    }

    @Test
    public void testProcessorWritesFormToCache() throws IOException {

        processor.getMarkup(sampleLibForm);
        assertEquals(htmlLoader.getHTMLContentOf("html/formCacheContent.html"), sampleLibForm.getCacheContents());

    }

    @Test
    public void testProcessorFormCache(){

        String computedMarkup = processor.getMarkup(sampleLibForm);
        String markUpFromCache = processor.getMarkup(sampleLibForm);
        assertEquals(computedMarkup, markUpFromCache);

    }

    @Test
    public void testProcessorFormWithInputsCache(){

        FormWithInputs stub = new FormWithInputs();
        String computedMarkup = processor.getMarkup(stub);
        String markUpFromCache = processor.getMarkup(stub);
        assertEquals(computedMarkup, markUpFromCache);

    }

    @Test
    public void testParagraphWithStringFormat() throws IOException {

        String actual = processor.getMarkup(new Paragraph("%s%s%s%s"));
        assertEquals(htmlLoader.getHTMLContentOf("html/paragraphWithStringFormat.html"), actual);

    }

    @Test
    public void testFormWithStringFormatActionCache() throws IOException {

        Form form = Form.builder()
                .addField(username)
                .addField(message)
                .method("post")
                .action("%s%s%s")
                .build();
        String actual = processor.getMarkup(form);
        // Doing this again to trigger cache call
        actual = processor.getMarkup(form);
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithStringFormatAction.html"), actual);

    }

    @Test
    public void testFormWithAllFormatAction() throws IOException {

        Form form = Form.builder()
                .addField(username)
                .addField(message)
                .method("post")
                .action(ALL_FORMATS)
                .build();
        String actual = processor.getMarkup(form);
        // Doing this again to trigger cache call
        actual = processor.getMarkup(form);
        assertEquals(htmlLoader.getHTMLContentOf("html/formWithAllFormatsAction.html"), actual);

    }

    @Test
    public void testParagraphWithPercents() throws IOException{

        assertEquals(htmlLoader.getHTMLContentOf("html/paragraphWithPercents.html"), processor.getMarkup(new Paragraph("%%%%%%%%%%")));

    }

    @Test
    public void testInputWithQuotes() throws IOException {

        Input in = new Input("myIn", "text", "\"<!--", "myIn");
        assertEquals(htmlLoader.getHTMLContentOf("html/inputWithHTMLEntities.html"), processor.getMarkup(in));

    }

    @Test
    public void testParagraphWithEntities() throws IOException {

        assertEquals(htmlLoader.getHTMLContentOf("html/paragraphWithEntities.html"), processor.getMarkup(new Paragraph("\"<!-- Hello World")));

    }

    @Test
    public void testSimpleStudentTable() throws IOException {

        List<Student> students = List.of(
                new Student(1, "John", 1.0f),
                new Student(2, "Ben", 4.0f)
        );
        assertEquals(htmlLoader.getHTMLContentOf("html/simpleTableOfPojoList.html"), processor.getMarkup(students, Student.class));

    }

    @Test
    public void testSimpleStudentWithTableAnnotationTable() throws IOException {

        List<StudentWithTableAnnotation> students = List.of(
                new StudentWithTableAnnotation(1, "John", 1.0f),
                new StudentWithTableAnnotation(2, "Ben", 4.0f)
        );
        assertEquals(htmlLoader.getHTMLContentOf("html/simpleTableOfAnnotatedPojo.html"),
                processor.getMarkup(students, StudentWithTableAnnotation.class));

    }

    @Test
    public void testSetAsTable() {

        Set<StudentWithTableAnnotation> set = Set.of(
                new StudentWithTableAnnotation(1, "John", 1.0f),
                new StudentWithTableAnnotation(2, "Ben", 4.0f)
        );
        String markup = processor.getMarkup(set, StudentWithTableAnnotation.class);
        /*
        Testing what the string contains instead of it as a whole because the set is unordered and so the order of table
        entries will vary
         */
        assertTrue(markup.contains("""
                        <tr>
                            <td>2</td>
                            <td>Ben</td>
                            <td>4.0</td>
                        </tr>
                """));
        assertTrue(markup.contains("""
                        <tr>
                            <td>1</td>
                            <td>John</td>
                            <td>1.0</td>
                        </tr>
                """));

        assertTrue(markup.contains("""
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Name</th>
                            <th>GPA</th>
                        </tr>
                    </thead>
                """));

    }

    @Test
    public void testPriorityQueueAsTable() throws IOException {

        Queue<StudentWithTableAnnotation> queue = new PriorityQueue<>();

        queue.add(new StudentWithTableAnnotation(1, "John", 1.0f));
        queue.add(new StudentWithTableAnnotation(2, "Ben", 4.0f));

        assertEquals(
                    htmlLoader.getHTMLContentOf("html/pojoTablePriorityQueue.html"),
                    processor.getMarkup(queue, StudentWithTableAnnotation.class)
                );

    }

}