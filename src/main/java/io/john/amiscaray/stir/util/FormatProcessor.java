package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.util.exceptions.TemplatingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Processes HTMLDocument formats, converting them to the corresponding markup
 */
public class FormatProcessor {

    private static FormatProcessor instance;
    private final ElementProcessor processor = ElementProcessor.getInstance();

    private FormatProcessor(){}

    public static FormatProcessor getInstance() {
        if (instance == null) {
            instance = new FormatProcessor();
        }
        return instance;
    }

    /**
     * Processes a document's elements and template to produce its HTML markup output
     * @param document The document to process
     * @return The document converted to HTML markup
     */
    public String processDocument(HTMLDocument document){

        String format = document.getFormat();

        // Split the string into the set of substrings starting and ending with format delimiters and those without the delimiters
        List<String> splitFormat = Arrays.asList(format.split("((?=<&)|(?<=&>))"));

        // Get the indentation size for the ith block corresponding to the ith element
        List<Integer> indentSizes = splitFormat.stream()
                .filter(str -> !(str.startsWith("<&") && str.endsWith("&>")))
                .mapToInt(str -> {
                    Pattern pattern = Pattern.compile("\s*$");
                    Matcher matcher = pattern.matcher(str);
                    assert matcher.find();
                    String trailing = matcher.group();
                    return trailing.length() / ElementProcessor.getIndentationSize();
                }).boxed().collect(Collectors.toList());

        List<String> newSplitFormat = new ArrayList<>();
        int i = 0;
        for (String str : splitFormat) {

            if (!(str.startsWith("<&") && str.endsWith("&>"))) {
                newSplitFormat.add(str);
            } else {
                int indentSize = indentSizes.get(i);
                newSplitFormat.add(processInnerTemplateBlock(str.substring(2, str.length() - 2), document, indentSize));
                i++;
            }

        }
        splitFormat = newSplitFormat;
        // TODO consider optimizing this with a StringBuilder
        return splitFormat.stream().reduce("", (s1, s2) -> s1 + s2);

    }

    private String processInnerTemplateBlock(String expression, HTMLDocument doc, Integer indentationSize){

        List<String> tokens = Arrays.asList(expression.split(" "));

        return tokens.stream()
                .map(token -> switch (token){
                    case "str_title" -> doc.getTitle();
                    case "str_content" -> processor.getMarkupForElementList(doc.getElements(), indentationSize);
                    case "str_meta" -> processor.getMarkupForElementList(doc.getMetaTags(), indentationSize);
                    case "str_hscripts" -> processor.getMarkupForElementList(doc.getHeaderScripts(), indentationSize);
                    case "str_fscripts" -> processor.getMarkupForElementList(doc.getFooterScripts(), indentationSize);
                    case "str_lang" -> doc.getLanguage();
                    case "str_styles" -> doc.getStyle() != null ? processor.getMarkup(doc.getStyle()) : "";
                    case "str_lstyles" -> processor.getMarkupForElementList(doc.getLinkedStyles(), indentationSize);
                    default -> {
                        if(token.isBlank()){
                            yield token;
                        }else {
                            throw new TemplatingException("Unexpected token in template: " + token);
                        }
                    }
                }).reduce("", (t1, t2) -> t1 + t2).trim();

    }

}
