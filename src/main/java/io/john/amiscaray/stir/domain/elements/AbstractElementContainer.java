package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A parent class for elements made to be containers of other elements (i.e., divs, headers, hgroup, etc.)
 * @param <T> The type of elements that may be children of the element
 */
public abstract class AbstractElementContainer <T extends AbstractUIElement> extends AbstractUIElement{

    @ChildList
    protected List<T> children;

    public AbstractElementContainer(String id, List<String> cssClasses, String style, List<T> children) {
        super(id, cssClasses, style);
        this.children = children;
    }

    /**
     * Adds a child to the element
     * @param child The child to add as a child of the element
     */
    public void addChild(T child){

        List<T> old = new ArrayList<>(children);
        List<T> newList = new ArrayList<>(children);
        newList.add(child);
        children = newList;
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

    /**
     * Removes a child from the element
     * @param child The child to remove from the element
     */
    public void removeChild(T child){

        List<AbstractUIElement> old = new ArrayList<>(children);
        children = children.stream()
                .filter(e -> !e.equals(child))
                .collect(Collectors.toList());
        propertyChangeSupport.firePropertyChange("children", old, children);

    }

    public List<T> getChildren() {
        return this.children;
    }
}
