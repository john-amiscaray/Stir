package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@HTMLElement(tagName = "div")
public class Div extends AbstractUIElement{

    @ChildList
    @Getter
    private List<AbstractUIElement> children;

    @Builder
    public Div(String id, @Singular List<String> cssClasses, String style, @Singular List<AbstractUIElement> children) {
        super(id, cssClasses, style);
        this.children = children;
    }

    public void addChild(AbstractUIElement child){

        List<AbstractUIElement> old = new ArrayList<>(children);
        List<AbstractUIElement> newList = new ArrayList<>(children);
        newList.add(child);
        children = newList;
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

    public void removeChild(AbstractUIElement child){

        List<AbstractUIElement> old = new ArrayList<>(children);
        children = children.stream()
                .filter(e -> !e.equals(child))
                .collect(Collectors.toList());
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

    public Footer toFooter(){

        return new Footer(id, cssClasses, style, children);

    }

}
