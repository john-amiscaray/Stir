package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.util.ElementProcessor;

import java.util.ArrayList;
import java.util.List;

public class HTMLDocument {

    private final List<Object> elements = new ArrayList<>();
    private String title;
    private final ElementProcessor processor = ElementProcessor.getInstance();

    public static Builder builder(){
        return new Builder();
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

        public HTMLDocument.Builder title(String title){
            doc.title = title;
            return this;
        }

        public HTMLDocument build(){
            return doc;
        }

    }

    public String generateDocumentString(){
        String builder = """
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>""" + (title != null ? title : "Title") +
                """
                </title>
                    </head>
                    <body>
                """ +
                "%s\n".repeat(elements.size()).trim() +
                """
                    </body>
                </html>""";
        return String.format(builder, elements.stream().map(element -> processor.getMarkup(element).indent(ElementProcessor.getIndentationSize() * 2)).toArray());
    }

}
