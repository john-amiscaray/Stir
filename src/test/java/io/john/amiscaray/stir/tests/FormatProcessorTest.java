package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.StudentWithTableAnnotation;
import io.john.amiscaray.stir.util.FormatProcessor;
import io.john.amiscaray.stir.util.exceptions.TemplatingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FormatProcessorTest {

    private final FormatProcessor formatProcessor = FormatProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final Div sampleContent = Div.builder()
            .child(new Heading(1, "Hello World"))
            .child(new Paragraph("This is the content"))
            .build();

    @Test
    public void testDocWithNoFormatExpressions() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Hello</title>
                                <style>
                                    body {
                                        color: red;
                                        background-color: #777777;
                                    }
                                </style>
                            </head>
                            <body>
                            </body>
                        </html>""")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithBasicStyles.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testDocWithTitleExpression() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <h1><& str_title &></h1>
                            </body>
                        </html>""")
                .title("Hello World")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithTitle.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testDocWithInvalidExpression() {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& str_title garbage &></title>
                            </head>
                            <body>
                                <h1><& str_title &></h1>
                            </body>
                        </html>""")
                .title("Hello World")
                .build();

        assertThrows(TemplatingException.class, () -> formatProcessor.processDocument(doc));

    }

    @Test
    public void testDocWithContentExpressionInMultilineBlock() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Hello World</title>
                            </head>
                            <body>
                                <&
                                
                                str_content
                                
                                &>
                            </body>
                        </html>""")
                .element(sampleContent)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docContentTemplateTest.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testMultilineFormatBlockWithTabs() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Hello World</title>
                            </head>
                            <body>
                                <&
                                
                                \t\t\tstr_content
                                
                                &>
                            </body>
                        </html>""")
                .element(sampleContent)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docContentTemplateTest.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testMalformedFormatDelimiter() {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Hello World</title>
                            </head>
                            <body>
                                <&--
                                
                                str_content
                                
                                --&>
                            </body>
                        </html>""")
                .element(sampleContent)
                .build();

        assertThrows(TemplatingException.class, () -> formatProcessor.processDocument(doc));

    }

    @Test
    public void testFormatBlockWithNoWhitespace() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title>Hello World</title>
                            </head>
                            <body>
                                <&str_content&>
                            </body>
                        </html>""")
                .element(sampleContent)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docContentTemplateTest.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testFormatWithAllBlocks() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="<& str_lang &>">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <& str_meta &>
                                <& str_hscripts &>
                                <& str_lstyles &>
                                <& str_styles &>
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <h1><& str_title &></h1>
                                <& str_content &>
                                <& str_fscripts &>
                            </body>
                        </html>""")
                .element(sampleContent)
                .title("Hello World")
                .language("fr")
                .footerScript(new Script("./main.js"))
                .headerScript(new Script("./script.js"))
                .linkedStyle(new LinkedStyle("./styles.css"))
                .style(new Style("""
                        div {
                            color: red;
                        }
                        """))
                .metaTag(Meta.builder()
                        .content("thing")
                        .name("sample")
                        .build())
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/formatWithAll.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testReadMeExample() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="<& str_lang &>">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <& str_meta &>
                                <& str_hscripts &>
                                <& str_lstyles &>
                                <& str_styles &>
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <hgroup>
                                    <h1><& str_title &></h1>
                                    <h2>Wowo Much Cool!</h2>
                                </hgroup>
                                <div class="content-wrapper">
                                    <& str_content &>
                                </div>
                                <& str_fscripts &>
                            </body>
                        </html>""")
                .element(sampleContent)
                .title("Hello World")
                .language("fr")
                .footerScript(new Script("./main.js"))
                .headerScript(new Script("./script.js"))
                .linkedStyle(new LinkedStyle("./styles.css"))
                .style(new Style("""
                        div {
                            color: red;
                        }
                        """))
                .metaTag(Meta.builder()
                        .content("thing")
                        .name("sample")
                        .build())
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/readmeExample.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testFormatWithCustomKey() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& my_text &></title>
                            </head>
                            <body>
                                <h1><& my_text &></h1>
                            </body>
                        </html>""")
                .formatArg("my_text", "Hello World")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithTitle.html"), formatProcessor.processDocument(doc));

    }

    @Test
    public void testFormatWithSpaceArg() {

        assertThrows(TemplatingException.class, () -> {
            HTMLDocument.builder()
                    .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& my text &></title>
                            </head>
                            <body>
                                <h1><& my text &></h1>
                            </body>
                        </html>""")
                    .formatArg("my text", "Hello World")
                    .build();
        });

    }

    @Test
    public void testFormatWithNewLineArg() {

        assertThrows(TemplatingException.class, () -> {
            HTMLDocument.builder()
                    .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& my
                                text &></title>
                            </head>
                            <body>
                                <h1><& my
                                text &></h1>
                            </body>
                        </html>""")
                    .formatArg("my\ntext", "Hello World")
                    .build();
        });

    }

    @Test
    public void testFormatArgStartsWithStr_(){

        assertThrows(TemplatingException.class, () -> {
            HTMLDocument.builder()
                    .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& str_my_text &></title>
                            </head>
                            <body>
                                <h1><& str_my_text &></h1>
                            </body>
                        </html>""")
                    .formatArg("str_my_text", "Hello World")
                    .build();
        });

    }

    @Test
    public void testFormatArgsNav() throws IOException {

        LinkedHashMap<String, String> navMap = new LinkedHashMap<>();
        navMap.put("Home", "/home");
        navMap.put("Products", "/products");
        navMap.put("About", "/about");

        List<StudentWithTableAnnotation> students = List.of(
                new StudentWithTableAnnotation(1, "Susan", 4.0f),
                new StudentWithTableAnnotation(2, "Karen", 0.1f),
                new StudentWithTableAnnotation(3, "George", 3.2f)
        );

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <& nav &>
                                <h1><& str_title &></h1>
                                <& str_content &>
                            </body>
                        </html>""")
                .title("My Document")
                .formatArg("nav", Nav.fromLabelHrefMap(navMap))
                .element(Div.builder()
                        .id("main-content")
                        .child(new Table(students, StudentWithTableAnnotation.class))
                        .build())
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/formatArgsNavTest.html"), formatProcessor.processDocument(doc));

    }

}
