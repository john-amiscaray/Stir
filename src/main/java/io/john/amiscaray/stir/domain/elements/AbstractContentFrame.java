package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;

import java.util.List;

/**
 * A parent class for any elements displaying external content
 */
public abstract class AbstractContentFrame extends AbstractUIElement {

    /**
     * The height attribute of the element
     */
    @Attribute(name = "height")
    protected Integer height;

    /**
     * The src attribute of the element
     */
    @Attribute(name = "src")
    protected String src;

    /**
     * The width attribute of the element
     */
    @Attribute(name = "width")
    protected Integer width;

    public AbstractContentFrame(String id, List<String> cssClasses, String style, Integer height, String src, Integer width) {
        super(id, cssClasses, style);
        this.height = height;
        this.src = src;
        this.width = width;
    }

    public AbstractContentFrame() {
    }

    public void setHeight(Integer height) {
        propertyChangeSupport.firePropertyChange("height", this.height, height);
        this.height = height;
    }

    public void setSrc(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public void setWidth(Integer width) {
        propertyChangeSupport.firePropertyChange("width", this.width, width);
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getSrc() {
        return this.src;
    }

    public Integer getWidth() {
        return this.width;
    }
}
