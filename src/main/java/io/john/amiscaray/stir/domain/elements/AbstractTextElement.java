package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.InnerContent;

import java.util.List;

public abstract class AbstractTextElement extends AbstractUIElement {

    @InnerContent
    protected String content;

    public AbstractTextElement(String id, List<String> cssClasses, String style, String content) {
        super(id, cssClasses, style);
        this.content = content;
    }

    public void setContent(String content) {
        propertyChangeSupport.firePropertyChange("content", this.content, content);
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

}
