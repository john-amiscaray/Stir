package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.element;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTMLDocumentTest {

    public static final String ALL_FORMATS = "%a%b%c%d%e%f%g%h%n%o%s%t%x";
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();
    private final Input username = new Input("text", "text", "John", null, "username");
    private final Input message = new Input("text1", "text", "Some Message", null, "message");

    private final Form sampleLibForm = Form.builder()
            .field(username)
            .field(message)
            .action("/path")
            .method("post")
            .build();

    private final CssRule animation = CssRule.builder()
            .selector("@keyframes example")
            .nestedRule(
                    CssRule.builder()
                            .selector("0%")
                            .style("background-color", "red")
                            .style("left", "0")
                            .style("top", "0")
                            .build()
            )
            .nestedRule(
                    CssRule.builder()
                            .selector("25%")
                            .style("background-color", "yellow")
                            .style("left", "200px")
                            .style("top", "0")
                            .build()
            )
            .nestedRule(
                    CssRule.builder()
                            .selector("50%")
                            .style("background-color", "blue")
                            .style("left", "200px")
                            .style("top", "200px")
                            .build()
            )
            .nestedRule(
                    CssRule.builder()
                            .selector("75%")
                            .style("background-color", "green")
                            .style("left", "0")
                            .style("top", "200px")
                            .build()
            )
            .nestedRule(
                    CssRule.builder()
                            .selector("100%")
                            .style("background-color", "red")
                            .style("left", "0")
                            .style("top", "0")
                            .build()
            )
            .build();

    private final String sampleCss = """
                /* Set the background color of body to tan */
                body {
                    background-color: tan;
                }              
                /* On screens that are 992px or less, set the background color to blue */
                @media screen and (max-width: 992px) {
                    body {
                        background-color: blue;
                    }
                }
                /* On screens that are 600px or less, set the background color to olive */
                @media screen and (max-width: 600px) {
                    body {
                        background-color: olive;
                    }
                }
                """;

    @Test
    public void testLibFormAsDocWithStyles() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(sampleLibForm)
                .linkedStyle(new LinkedStyle(
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css",
                        "sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65",
                        "anonymous"
                ))
                .linkedStyle(new LinkedStyle("./styles.css"))
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/libFormAsDocExpected.html"), doc.generateDocumentString());

    }

    @Test
    public void testLibFormAsDocWithFooterScript() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(sampleLibForm)
                .footerScript(new Script("./main.js"))
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithFooterScriptExpected.html"), doc.generateDocumentString());

    }

    @Test
    public void testLibFormAsDocWithHeaderScript() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(sampleLibForm)
                .headerScript(new Script("./main.js"))
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithHeaderScriptExpected.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithHeaderScriptAndStyles() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(sampleLibForm)
                .linkedStyle(new LinkedStyle(
                        "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css",
                        "sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65",
                        "anonymous"
                ))
                .linkedStyle(new LinkedStyle("./styles.css"))
                .headerScript(new Script("./main.js"))
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithHeaderScriptAndStyleExpected.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithMeta() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .metaTag(new Meta("viewport", "width=device-width, initial-scale=1.0"))
                .metaTag(new Meta("robots", "noindex"))
                .metaTag(Meta.builder().httpEquiv("refresh").content("30").build())
                .title("Hello")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithMeta.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithParagraphStringFormat() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(new Paragraph("%s%s%s"))
                .title("%s%s%s")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithStringFormat.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithInputStringFormat() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .element(new Input("myText", "text", "%s%s%s", "myText", "text"))
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithInputStringFormat.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithBasicStyles() throws IOException {

        CssRule background = CssRule.builder()
                .selector("body")
                .style("color", "red")
                .style("background-color", "#777777")
                .build();
        Style style = Style.builder()
                .rule(background)
                .build();
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithBasicStyles.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithCssAnimation() throws IOException {

        CssRule animation = CssRule.builder()
                .selector("@keyframes example")
                .nestedRule(
                        CssRule.builder()
                                .selector("0%")
                                .style("background-color", "red")
                                .style("left", "0")
                                .style("top", "0")
                                .build()
                )
                .nestedRule(
                        CssRule.builder()
                                .selector("25%")
                                .style("background-color", "yellow")
                                .style("left", "200px")
                                .style("top", "0")
                                .build()
                )
                .nestedRule(
                        CssRule.builder()
                                .selector("50%")
                                .style("background-color", "blue")
                                .style("left", "200px")
                                .style("top", "200px")
                                .build()
                )
                .nestedRule(
                        CssRule.builder()
                                .selector("75%")
                                .style("background-color", "green")
                                .style("left", "0")
                                .style("top", "200px")
                                .build()
                )
                .nestedRule(
                        CssRule.builder()
                                .selector("100%")
                                .style("background-color", "red")
                                .style("left", "0")
                                .style("top", "0")
                                .build()
                )
                .build();
        Style style = Style.builder()
                .rule(animation)
                .build();
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithCssAnimation.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithStyleContent() throws IOException{

        CssRule body = CssRule.builder()
                .selector("body")
                .style("content", "'Hello World &&&'")
                .build();

        Style style = Style.builder()
                .rule(body)
                .build();
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithStyleContent.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithStyleLiteral() throws IOException {

        Style style = new Style(sampleCss);
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithStyleLiteral.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithStyleLiteralAndBuiltStyle() throws IOException{

        Style style = Style.builder()
                .rule(animation)
                .literalCssString(sampleCss)
                .build();
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithMixedCss.html"), doc.generateDocumentString());

    }

    @Test
    public void testAddBuiltStyleToEnd() throws IOException{

        Style style = Style.builder()
                .literalCssString(sampleCss)
                .rule(animation)
                .build();
        HTMLDocument doc = HTMLDocument.builder()
                .style(style)
                .title("Hello")
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithMixedCss2.html"), doc.generateDocumentString());

    }

    @Test
    public void testSetLanguageToRussian() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .language("ru")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithLanguageRussian.html"), doc.generateDocumentString());

    }

    @Test
    public void testSetLanguageWithMaliciousCharacters() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .language("en\"><script>alert('Hello World!')</script></html><!--")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithLanguageMalicious.html"), doc.generateDocumentString());

    }

    @Test
    public void testSetLanguageWithStringFormats() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .language(ALL_FORMATS)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docLanguageWithStringFormats.html"), doc.generateDocumentString());

    }

    @Test
    public void testSetTitleMalicious() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .title("Hello</title></head><body><script>alert('I did the thing!');</script></body><!--")
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithTitleMalicious.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithStudentTable() throws IOException{

        List<Student> students = List.of(
                new Student(1, "John", 1.0f),
                new Student(2, "Ben", 4.0f)
        );
        HTMLDocument doc = HTMLDocument.builder()
                .element(new Table(students, Student.class))
                .build();
        assertEquals(htmlLoader.getHTMLContentOf("html/docWithStudentTable.html"), doc.generateDocumentString());

    }

    @Test
    public void testEmptyDocWithBootStrapSupportWithPopper() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .withBootStrap(true)
                .withBootStrapPopper(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyDocWithBootstrap.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithBootStrapSupportAndTable() throws IOException{

        List<Student> students = List.of(new Student(1, "John", 4.0f));

        HTMLDocument doc = HTMLDocument.builder()
                .element(new Table(students, Student.class))
                .withBootStrap(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithTableAndBootstrap.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithCSSWaterDefault() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .withWaterCSS(true)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithCSSWaterDefault.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithCSSWaterDark() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .withWaterCSS(true)
                .waterCSSTheme(HTMLDocument.ColorTheme.DARK)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithCSSWaterDark.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithCSSWaterLight() throws IOException{

        HTMLDocument doc = HTMLDocument.builder()
                .withWaterCSS(true)
                .waterCSSTheme(HTMLDocument.ColorTheme.LIGHT)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithCSSWaterLight.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithCSSWaterAuto() throws IOException {

        HTMLDocument doc = HTMLDocument.builder()
                .withWaterCSS(true)
                .waterCSSTheme(HTMLDocument.ColorTheme.AUTO)
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docWithCSSWaterDefault.html"), doc.generateDocumentString());

    }

    @Test
    public void testDocWithBodyFormat() {

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <& nav &>
                        <h1><& str_title &></h1>
                        <& str_content &>
                        """)
                .isFormatForBody(true)
                .build();

        assertEquals("""
            <!DOCTYPE html>
            <html lang="<& str_lang &>">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <& str_meta &>
                    <title><& str_title &></title>
                    <& str_hscripts &>
                    <& str_lstyles &>
                    <& str_styles &>
                </head>
                <body>
                    <& nav &>
                    <h1><& str_title &></h1>
                    <& str_content &>
                    <& str_fscripts &>
                </body>
            </html>""", doc.getFormat());

    }

    @Test
    public void testDocBodyFormatWithAll() throws IOException {

        LinkedHashMap<String, String> navMap = new LinkedHashMap<>();
        navMap.put("Home", "/home");
        navMap.put("Products", "/products");
        navMap.put("About", "/about");
        Nav nav = Nav.fromLabelHrefMap(navMap);
        Table studentTable = new Table(List.of(new Student(1, "John", 3.99f)), Student.class);
        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <& nav &>
                        <h1><& str_title &></h1>
                        <& str_content &>
                        """)
                .isFormatForBody(true)
                .formatArg("nav", nav)
                .element(studentTable)
                .headerScript(new Script("./main.js"))
                .metaTag(Meta.builder().name("something").content("a thing").build())
                .footerScript((Script) element("script[src='./thing.js']"))
                .title("Hello World")
                .language("en")
                .style(Style.builder()
                        .literalCssString("""
                                body {
                                    color: red;
                                }
                                """)
                        .build())
                .linkedStyle(new LinkedStyle("./style.css"))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/docBodyFormatTest.html"), doc.generateDocumentString());

    }

}
