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
    protected List<String> classList = new ArrayList<>();

    @Attribute(name = "style")
    private String style;

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

    public List<String> getClassList() {
        return classList;
    }

    public void setId(String id) {
        propertyChangeSupport.firePropertyChange("id", this.id, id);
        this.id = id;
    }

    public void setClassList(List<String> classList) {
        propertyChangeSupport.firePropertyChange("classList", this.classList, classList);
        this.classList = classList;
    }

    public void addClass(String clazz){

        List<String> old = (List<String>) ((ArrayList<String>) classList).clone();
        classList.add(clazz);
        propertyChangeSupport.firePropertyChange("classList", old, classList);

    }

}
