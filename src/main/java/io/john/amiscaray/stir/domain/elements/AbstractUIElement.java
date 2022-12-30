package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.Id;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUIElement extends CacheableElement {

    @Id
    protected String id;

    @ClassList
    protected List<String> cssClasses = new ArrayList<>();

    @Attribute(name = "style")
    protected String style;

    public AbstractUIElement(String id, List<String> cssClasses, String style) {
        this.id = id;
        this.cssClasses = cssClasses;
        this.style = style;
    }

    public AbstractUIElement() {
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        propertyChangeSupport.firePropertyChange("style", this.style, style);
        this.style = style;
    }

    public String getId() {
        return id;
    }

    public List<String> getCssClasses() {
        return cssClasses;
    }

    public void setId(String id) {
        propertyChangeSupport.firePropertyChange("id", this.id, id);
        this.id = id;
    }

    public void setCssClasses(List<String> cssClasses) {
        propertyChangeSupport.firePropertyChange("classList", this.cssClasses, cssClasses);
        this.cssClasses = cssClasses;
    }

    public void addClass(String clazz){

        List<String> old = new ArrayList<>(cssClasses);
        cssClasses.add(clazz);
        propertyChangeSupport.firePropertyChange("classList", old, cssClasses);

    }

}
