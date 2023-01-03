package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.util.FormatProcessor;
import io.john.amiscaray.stir.util.exceptions.TemplatingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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

}
