package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.Id;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The base class for all classes that represent HTML elements
 */
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractUIElement extends CacheableElement {

    /**
     * The ID of the element
     */
    @Id
    protected String id;

    /**
     * A list of CSS classes for the element
     */
    @ClassList
    protected List<String> cssClasses = new ArrayList<>();

    /**
     * The style attribute of the element
     */
    @Attribute(name = "style")
    protected String style;

    /**
     * Whether the element should be hidden. Setting this to true adds the following style to it: display: none.
     */
    protected boolean hidden;

    protected Map<String, String> customAttributes = new TreeMap<>();

    public AbstractUIElement(String id, List<String> cssClasses, String style, boolean hidden) {
        this.id = id;
        if(cssClasses != null){
            this.cssClasses = new ArrayList<>(cssClasses);
        }
        this.style = style;
        this.hidden = hidden;
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

    public void setHidden(boolean hidden) {
        propertyChangeSupport.firePropertyChange("hidden", this.hidden, hidden);
        this.hidden = hidden;
    }

    /**
     * Adds a CSS class to the element
     * @param clazz The CSS class to add to the element
     */
    public void addClass(String clazz){

        List<String> old = new ArrayList<>(cssClasses);
        cssClasses.add(clazz);
        propertyChangeSupport.firePropertyChange("classList", old, cssClasses);

    }

    public boolean isHidden() {
        return this.hidden;
    }
}
