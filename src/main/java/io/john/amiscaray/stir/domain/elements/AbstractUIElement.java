package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractUIElement extends CacheableElement {

    @Id
    protected String id;

    @ClassList
    protected List<String> classes = new ArrayList<>();

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

    public List<String> getClasses() {
        return classes;
    }

    public void setId(String id) {
        propertyChangeSupport.firePropertyChange("id", this.id, id);
        this.id = id;
    }

    public void setClasses(List<String> classes) {
        propertyChangeSupport.firePropertyChange("classList", this.classes, classes);
        this.classes = classes;
    }

    public void addClass(String clazz){

        List<String> old = (List<String>) ((ArrayList<String>) classes).clone();
        classes.add(clazz);
        propertyChangeSupport.firePropertyChange("classList", old, classes);

    }

}
