package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.domain.elements.Meta;
import io.john.amiscaray.stir.domain.elements.Script;
import io.john.amiscaray.stir.domain.elements.Style;
import io.john.amiscaray.stir.util.ElementProcessor;

import java.util.ArrayList;
import java.util.List;

public class HTMLDocument {

    private final List<Object> elements = new ArrayList<>();
    private final List<Style> styleSheets = new ArrayList<>();
    private final List<Script> headerScripts = new ArrayList<>();
    private final List<Script> footerScripts = new ArrayList<>();
    private final List<Meta> metaList = new ArrayList<>();
    private String title;
    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final static String format =
            """
            <!DOCTYPE html>
            <html lang="en">
                <head>
                    <meta charset="UTF-8">%s
                    <title>%s</title>%s%s
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

        public HTMLDocument.Builder addElement(Object element){
            doc.elements.add(element);
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

        public HTMLDocument.Builder title(String title){
            doc.title = title;
            return this;
        }

        public HTMLDocument.Builder addStyle(Style style){
            doc.styleSheets.add(style);
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
        String metaMarkup = processor.getMarkupForElementList(metaList, 2);
        String finalTitle = (title != null ? title : "Title");
        String headerScriptsMarkup = processor.getMarkupForElementList(headerScripts, 2);
        String stylesMarkup = processor.getMarkupForElementList(styleSheets, 2);
        String elementsMarkup = processor.getMarkupForElementList(elements, 2);
        String footerScriptsMarkup = processor.getMarkupForElementList(footerScripts, 2);
        return String.format(format,
                !metaMarkup.isEmpty() ? "\n" + metaMarkup : "",
                finalTitle,
                !headerScriptsMarkup.isEmpty() ? "\n" + headerScriptsMarkup : "",
                !stylesMarkup.isEmpty() ? "\n" + stylesMarkup : "",
                !elementsMarkup.isEmpty() ? "\n" + elementsMarkup : "",
                !footerScriptsMarkup.isEmpty() ? "\n" + footerScriptsMarkup : ""
        );
    }

}
