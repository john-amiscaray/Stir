package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.exceptions.ElementInitializationException;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import io.john.amiscaray.stir.util.exceptions.TemplatingException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.*;

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
     * Processes a document's elements and template to produce its HTML markup output. See the <a href="https://github.com/john-amiscaray/Stir#document-templating">README</a> for more info
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
                    boolean foundMatch = matcher.find();
                    assert foundMatch;
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
        // The last call to replaceAll removes any empty lines
        return splitFormat.stream().reduce("", (s1, s2) -> s1 + s2).replaceAll("(?m)^[ \t]*\r?\n", "");

    }

    /**
     * Parses the inner content of a template block, converting it to the appropriate HTML content
     * @param expression The expression to parse
     * @param doc The HTML document being formatted
     * @param indentationSize The indentation size for the content
     * @return The resulting content
     */
    private String processInnerTemplateBlock(String expression, HTMLDocument doc, Integer indentationSize){

        List<String> tokens = Arrays.asList(expression.split("(?<!element\\()( +|\\n)(?![^(]*\\))"));

        return tokens.stream()
                .map(token -> switch (token.trim()){
                    case "str_title" -> processor.encodeForEntitiesOnly(doc.getTitle().indent(indentationSize * ElementProcessor.getIndentationSize())) + "\n";
                    case "str_content" -> processor.getMarkupForElementList(doc.getElements(), indentationSize) + "\n";
                    case "str_meta" -> processor.getMarkupForElementList(doc.getMetaTags(), indentationSize);
                    case "str_hscripts" -> processor.getMarkupForElementList(doc.getHeaderScripts(), indentationSize);
                    case "str_fscripts" -> processor.getMarkupForElementList(doc.getFooterScripts(), indentationSize);
                    case "str_lang" -> processor.encodeForEntitiesOnly(doc.getLanguage().indent(indentationSize * ElementProcessor.getIndentationSize())) + "\n";
                    case "str_styles" -> doc.getStyle() != null ? processor.getMarkup(doc.getStyle()).indent(indentationSize * ElementProcessor.getIndentationSize()) : "";
                    case "str_lstyles" -> processor.getMarkupForElementList(doc.getLinkedStyles(), indentationSize);
                    default -> {
                        if(token.isBlank()){
                            yield token;
                        } else {
                            Map<String, Object> formatArgs = doc.getFormatArgs();
                            if(formatArgs.containsKey(token)){
                                Object value = formatArgs.get(token);
                                if(value instanceof AbstractUIElement){
                                    yield processor.getMarkup((AbstractUIElement) value).indent(indentationSize * ElementProcessor.getIndentationSize());
                                }
                                yield formatArgs.get(token).toString();
                            }else{

                                if(!token.matches("element(.*)")){
                                    throw new TemplatingException("Unexpected token: " + token + " in the format. If this was meant to be an element descriptor, remember that it must be enclosed in 'element()'.");
                                }
                                assert token.length() > 8;
                                token = token.substring(8, token.length() - 1);
                                yield processor.getMarkup(element(token)).indent(indentationSize * ElementProcessor.getIndentationSize());

                            }
                        }
                    }
                }).reduce("", (t1, t2) -> t1 + t2).trim();

    }

}
