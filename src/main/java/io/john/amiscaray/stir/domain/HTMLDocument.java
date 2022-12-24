package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.util.ElementProcessor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
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
            doc.elements.add(new Table(collection, clazz));
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

    /**
     * Retrieves a list of elements added to the document that match with the given CSS query. Note that this implementation
     * does not currently support pseudo-elements nor pseudo-classes. TODO add support for attribute selectors
     * @param query The CSS selector to select elements with
     * @return All elements that match that query
     */
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

        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.\\\"]*\\[.*\\]|[^ \\[\\]]+");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()){
            tokens.add(matcher.group());
        }

        List<AbstractUIElement> lastResult = null;
        String lastToken = null;
        for (String token : tokens) {
            if(!isCssSelectorOperator(token)){
                if(lastResult == null){
                    lastResult = processToken(token, elements);
                }else{
                    switch (lastToken) {
                        case ">" -> lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(HTMLDocument::getAllDirectDescendents)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        );
                        case "+" -> lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(element -> {
                                            int idx = elements.indexOf(element);
                                            return idx < elements.size() - 1 ? elements.get(idx + 1) : null;
                                        })
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList())
                        );
                        case "~" -> {
                            if (lastResult.isEmpty()) {
                                continue;
                            }
                            AbstractUIElement lastElement = lastResult.get(0);
                            int indexOfLastEl = elements.indexOf(lastElement);
                            lastResult = processToken(
                                    token,
                                    elements.stream()
                                            .filter(element -> {
                                                int idx = elements.indexOf(element);
                                                return idx > indexOfLastEl && !processToken(token, List.of(element)).isEmpty();
                                            })
                                            .collect(Collectors.toList())
                            );
                        }
                        default -> lastResult = processToken(
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
            elements
                    .stream()
                    .map(HTMLDocument::getAllDirectDescendents)
                    .map(list -> processQuery(query, list))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList())
        );

        return new ArrayList<>(new LinkedHashSet<>(finalResult));

    }

    private static List<AbstractUIElement> processToken(String query, List<AbstractUIElement> elements){

        Pattern pattern = Pattern.compile("(\\[[^\\[\\]]+])+$");
        Matcher matcher = pattern.matcher(query);
        if(matcher.find()){
            int idx = matcher.start();
            String attributeSelectors = query.substring(idx);
            elements = processAttributeSelector(attributeSelectors, elements);
            query = query.substring(0, idx);
        }

        if(!query.contains("#") && !query.contains(".")){
            return findAllOfTagName(query, elements);
        }

        List<Character> terminators = List.of('#', '.');
        List<String> names = Arrays.stream(query.split("[#.]"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        List<Character> queryTypes = query.chars()
                .mapToObj(e -> (char) e)
                .filter(terminators::contains)
                .collect(Collectors.toList());

        assert names.size() == queryTypes.size();

        List<AbstractUIElement> currentElements = elements;

        for(int i = 0; i < names.size(); i++){

            if(queryTypes.get(i).equals('#')){
                currentElements = findAllOfID(names.get(i), currentElements);
            }else if(queryTypes.get(i).equals('.')){
                currentElements = findAllOfClass(names.get(i), currentElements);
            }

        }

        return currentElements;

    }

    private static List<AbstractUIElement> findAllOfTagName(String tagName, List<AbstractUIElement> elements){

        if(tagName.equals("*")){
            return elements;
        }

        return elements.stream().filter(element -> {
            Class<?> clazz = element.getClass();
            if(!clazz.isAnnotationPresent(HTMLElement.class) && !(element instanceof Table)){
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

    public static List<AbstractUIElement> processAttributeSelector(String query, List<AbstractUIElement> elements){

        // TODO make this pattern not match any spaces in the square brackets and not in quotes
        Pattern pattern = Pattern.compile("\\[[^\\[\\]]+]");
        Matcher matcher = pattern.matcher(query);
        //List<String> operators = List.of("=", "~=", "|=", "^=", "$=", "*=");
        List<AbstractUIElement> currentElements = elements;
        while(matcher.find()){
            String match = matcher.group();
            match = match.substring(1, match.length() - 1);
            String[] keyValue = match.split("=|~=|\\|=|\\^=|\\$=|\\*=", 2);
            assert keyValue.length == 2 || keyValue.length == 1;
            String key = keyValue[0];
            String value = keyValue.length == 2 ? keyValue[1] : null;
            if(value != null && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"'){
                value = value.substring(1, value.length() - 1);
            }
            if(match.contains("~=")){
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> {
                    if(v.equals(finalValue)){
                        return true;
                    }
                    String pat = "(^" + finalValue + " )|( " + finalValue + " )|( "+ finalValue +"$)";
                    Pattern p = Pattern.compile(pat);
                    Matcher m = p.matcher(v);
                    assert finalValue != null;
                    return m.find() && v.contains(finalValue);
                }, currentElements);
            }else if(match.contains("|=")) {
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> {
                        if(v.equals(finalValue)){
                            return true;
                        }
                        Pattern p = Pattern.compile(".+(-.*)+");
                        Matcher m = p.matcher(v);
                        assert finalValue != null;
                        return m.matches() && v.contains(finalValue);
                    }, currentElements);
            }else if(match.contains("^=")) {
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.startsWith(finalValue), currentElements);
            }else if(match.contains("$=")){
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.endsWith(finalValue), currentElements);
            }else if(match.contains("*=")) {
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.contains(finalValue), currentElements);
            }else if(match.contains("=")) {
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> v.equals(finalValue), currentElements);
            }else{
                currentElements = filterForAttributes(key, v -> true, currentElements);
            }
        }

        return currentElements;

    }

    private static boolean isCssSelectorOperator(String token){

        Pattern pattern = Pattern.compile("^[>+~]$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();

    }

    private static List<AbstractUIElement> filterForAttributes(String attributeName, Predicate<String> predicate,
                                                               List<AbstractUIElement> elements){

        ElementProcessor processor = ElementProcessor.getInstance();

        return elements.stream()
                .filter(element -> {

                    if(attributeName.equals("class")){
                        String classAttValue = element.getClassList().stream().reduce("", (s1, s2) -> s1 + " " + s2).trim();
                        return element.getClassList() != null
                                && !element.getClassList().isEmpty()
                                && predicate.test(classAttValue);
                    }else if(attributeName.equals("id")){
                        return element.getId() != null && predicate.test(element.getId());
                    }

                    Field[] fields = processor.getAllFields(element.getClass());
                    for(Field field : fields){
                        field.setAccessible(true);
                        if(!field.isAnnotationPresent(Attribute.class) || !attributeName.equals(field.getName())){
                            continue;
                        }
                        try {
                            return predicate.test(field.get(element).toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
    }

}
