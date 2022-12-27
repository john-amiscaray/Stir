package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public abstract class AbstractContentFrame extends AbstractUIElement {

    @Attribute(name = "height")
    @Getter
    protected Integer height;
    @Attribute(name = "src")
    @Getter
    protected String src;
    @Attribute(name = "width")
    @Getter
    protected Integer width;

    public AbstractContentFrame(String id, List<String> cssClasses, String style, Integer height, String src, Integer width) {
        super(id, cssClasses, style);
        this.height = height;
        this.src = src;
        this.width = width;
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

}
