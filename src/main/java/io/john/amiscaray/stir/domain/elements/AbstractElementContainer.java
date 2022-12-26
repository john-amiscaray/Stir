package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractElementContainer <T extends AbstractUIElement> extends AbstractUIElement{

    @ChildList
    @Getter
    protected List<T> children;

    public AbstractElementContainer(String id, List<String> cssClasses, String style, List<T> children) {
        super(id, cssClasses, style);
        this.children = children;
    }

    public void addChild(T child){

        List<T> old = new ArrayList<>(children);
        List<T> newList = new ArrayList<>(children);
        newList.add(child);
        children = newList;
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

    public void removeChild(T child){

        List<AbstractUIElement> old = new ArrayList<>(children);
        children = children.stream()
                .filter(e -> !e.equals(child))
                .collect(Collectors.toList());
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

}
