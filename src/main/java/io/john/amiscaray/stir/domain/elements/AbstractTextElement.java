package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A parent class for elements meant to display text
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTextElement extends AbstractUIElement {

    /**
     * The inner content of the element
     */
    @InnerContent
    protected String content;

    public AbstractTextElement(String id, List<String> cssClasses, String style, String content, boolean hidden) {
        super(id, cssClasses, style, hidden);
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
