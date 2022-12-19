package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class HTMLDocument {

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

    private List<AbstractUIElement> querySelector(String query, List<AbstractUIElement> elements){

        String[] subQueries = query.split(",");
        return Arrays.stream(subQueries)
                .map(subQuery -> processQuery(subQuery, elements))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    private List<AbstractUIElement> processQuery(String query, List<AbstractUIElement> elements){

        if(query.startsWith("#")){
            return findAllOfID(query.substring(1), elements);
        }else if(query.startsWith(".")) {
            return findAllOfClass(query.substring(1), elements);
        }else{
            return findAllOfTagName(query, elements);
        }

    }

    private List<AbstractUIElement> findAllOfTagName(String tagName, List<AbstractUIElement> elements){

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

    private List<AbstractUIElement> findAllOfID(String id, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getId() != null && element.getId().equals(id)).collect(Collectors.toList());

    }

    private List<AbstractUIElement> findAllOfClass(String clazz, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getClassList().contains(clazz)).collect(Collectors.toList());

    }

}
