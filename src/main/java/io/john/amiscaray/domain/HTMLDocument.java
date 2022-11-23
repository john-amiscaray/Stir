package io.john.amiscaray.domain;

import io.john.amiscaray.util.ElementProcessor;

import java.util.ArrayList;
import java.util.List;

public class HTMLDocument {

    private final List<Object> elements = new ArrayList<>();
    private final ElementProcessor processor = ElementProcessor.getInstance();

    public static class Builder{

        private final HTMLDocument doc;

        public Builder(){
            doc = new HTMLDocument();
        }

        public HTMLDocument.Builder addElement(Object element){
            doc.elements.add(element);
            return this;
        }

        public HTMLDocument build(){
            return doc;
        }

    }

}
