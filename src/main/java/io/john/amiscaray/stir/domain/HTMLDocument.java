package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.util.ElementProcessor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HTMLDocument {

    @Getter
    private final List<AbstractUIElement> elements = new ArrayList<>();
    private final List<LinkedStyle> linkedStyles = new ArrayList<>();
    private Style style;
    private final List<Script> headerScripts = new ArrayList<>();
    private final List<Script> footerScripts = new ArrayList<>();
    private final List<Meta> metaList = new ArrayList<>();
    private String title;
    private final ElementProcessor processor = ElementProcessor.getInstance();
    private String language = "en";
    private final static String format =
            """
            <!DOCTYPE html>
            <html lang="%s">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">%s
                    <title>%s</title>%s%s%s
                </head>
                <body>%s%s
                </body>
            </html>""";

    public static Builder builder(){
        return new Builder();
    }

    public enum DocumentPosition{
        HEADER,
        FOOTER
    }

    public static class Builder{

        private final HTMLDocument doc;

        public Builder(){
            doc = new HTMLDocument();
        }

        public HTMLDocument.Builder addElement(AbstractUIElement element){
            doc.elements.add(element);
            return this;
        }

        public HTMLDocument.Builder addCollectionAsTable(Collection<?> collection, Class<?> clazz){
            doc.elements.add(new CollectionTableAdapter(collection, clazz));
            return this;
        }

        public HTMLDocument.Builder addScript(Script script, DocumentPosition position){
            if(position.equals(DocumentPosition.HEADER)){
                doc.headerScripts.add(script);
            }else{
                doc.footerScripts.add(script);
            }
            return this;
        }

        public HTMLDocument.Builder language(String language){
            doc.language = language;
            return this;
        }

        public HTMLDocument.Builder title(String title){
            doc.title = title;
            return this;
        }

        public HTMLDocument.Builder addLinkedStyle(LinkedStyle style){
            doc.linkedStyles.add(style);
            return this;
        }

        public HTMLDocument.Builder style(Style style){
            doc.style = style;
            return this;
        }

        public HTMLDocument.Builder addMeta(Meta meta){
            doc.metaList.add(meta);
            return this;
        }

        public HTMLDocument build(){
            return doc;
        }

    }

    public String generateDocumentString(){
        String finalLanguage = processor.encodeForEntitiesOnly(language != null && !language.isEmpty() ? language : "en");
        String metaMarkup = processor.getMarkupForElementList(metaList, 2);
        String finalTitle = processor.encodeForEntitiesOnly(title != null ? title : "Title");
        String headerScriptsMarkup = processor.getMarkupForElementList(headerScripts, 2);
        String linkedStylesMarkup = processor.getMarkupForElementList(linkedStyles, 2);
        String styleMarkup = style != null ? "\n" + processor.getMarkup(style).indent(ElementProcessor.getIndentationSize() * 2).stripTrailing() : "";
        String elementsMarkup = processor.getMarkupForElementList(elements, 2);
        String footerScriptsMarkup = processor.getMarkupForElementList(footerScripts, 2);
        return String.format(format,
                finalLanguage,
                !metaMarkup.isEmpty() ? "\n" + metaMarkup : "",
                finalTitle,
                !headerScriptsMarkup.isEmpty() ? "\n" + headerScriptsMarkup : "",
                !linkedStylesMarkup.isEmpty() ? "\n" + linkedStylesMarkup : "",
                styleMarkup,
                !elementsMarkup.isEmpty() ? "\n" + elementsMarkup : "",
                !footerScriptsMarkup.isEmpty() ? "\n" + footerScriptsMarkup : ""
        );
    }

    public List<AbstractUIElement> querySelector(String query){

        return querySelector(query, this.elements);

    }

    private static List<AbstractUIElement> querySelector(String query, List<AbstractUIElement> elements){

        String[] subQueries = query.split(",");
        return Arrays.stream(subQueries)
                .map(subQuery -> processQuery(subQuery, elements))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private static List<AbstractUIElement> processQuery(String query, List<AbstractUIElement> elements){

        if(elements.isEmpty()){
            return elements;
        }

        String[] tokens = query.split(" ");
        List<AbstractUIElement> lastResult = null;
        String lastToken = null;
        for (String token : tokens) {
            if(!isCssSelectorOperator(token)){
                if(lastResult == null){
                    lastResult = processToken(token, elements);
                }else{
                    if(lastToken.equals(">")){
                        lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(HTMLDocument::getAllDirectDescendents)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        );
                    }else if (lastToken.equals("+")){
                        lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(element -> {
                                            int idx = elements.indexOf(element);
                                            return idx < elements.size() - 1 ? elements.get(idx + 1) : null;
                                        })
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList())
                        );
                    }else{
                        lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(HTMLDocument::getAllDescendents)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }

            lastToken = token;
        }

        List<AbstractUIElement> finalResult = new ArrayList<>();
        if(lastResult != null){
            finalResult.addAll(lastResult);
        }
        finalResult.addAll(
                processQuery(query,
                        elements
                                .stream()
                                .map(HTMLDocument::getAllDirectDescendents)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())
                )
        );

        return new ArrayList<>(new LinkedHashSet<>(finalResult));

    }

    private static List<AbstractUIElement> processToken(String query, List<AbstractUIElement> elements){

        if(query.startsWith("#")){
            return findAllOfID(query.substring(1), elements);
        }else if(query.startsWith(".")) {
            return findAllOfClass(query.substring(1), elements);
        }else{
            return findAllOfTagName(query, elements);
        }

    }

    private static List<AbstractUIElement> findAllOfTagName(String tagName, List<AbstractUIElement> elements){

        if(tagName.equals("*")){
            return elements;
        }

        return elements.stream().filter(element -> {
            Class<?> clazz = element.getClass();
            if(!clazz.isAnnotationPresent(HTMLElement.class) && !(element instanceof CollectionTableAdapter)){
                throw new IllegalElementException("The class " + clazz.getName() + " is not marked as a valid UI HTML element");
            }
            String elementTag = clazz.isAnnotationPresent(HTMLElement.class) ?
                    clazz.getAnnotation(HTMLElement.class).tagName() :
                    "table";
            // The element must be a table if it does not have the HTMLElement annotation
            return tagName.equals(elementTag);

        }).collect(Collectors.toList());

    }

    private static List<AbstractUIElement> findAllOfID(String id, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getId() != null && element.getId().equals(id)).collect(Collectors.toList());

    }

    private static List<AbstractUIElement> findAllOfClass(String clazz, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getClassList().contains(clazz)).collect(Collectors.toList());

    }

    public static List<AbstractUIElement> getAllDirectDescendents(AbstractUIElement ancestor){

        Class<?> clazz = ancestor.getClass();
        List<AbstractUIElement> children = new ArrayList<>();
        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Nested.class)) {
                    children.add((AbstractUIElement) field.get(ancestor));
                }else if(field.isAnnotationPresent(ChildList.class)) {
                    List<AbstractUIElement> childList = (List<AbstractUIElement>) field.get(ancestor);
                    children.addAll(childList);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return children;

    }

    public static List<AbstractUIElement> getAllDescendents(AbstractUIElement ancestor){

        List<AbstractUIElement> directDescendents = getAllDirectDescendents(ancestor);
        return Stream.concat(directDescendents.stream()
                .map(HTMLDocument::getAllDescendents)
                .flatMap(Collection::stream), directDescendents.stream())
                .collect(Collectors.toList());

    }

    private static boolean isCssSelectorOperator(String token){

        Pattern pattern = Pattern.compile("^[>+~]$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();

    }

}
