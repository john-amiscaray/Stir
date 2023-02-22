package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A parent class for the two list types (ul and ol)
 */
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractListElement extends AbstractUIElement{

    /**
     * The inner list items of the list
     */
    @ChildList
    protected List<ListItem> listItems;

    public AbstractListElement(String id, List<String> cssClasses, String style, List<ListItem> listItems, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.listItems = listItems;
    }

    public AbstractListElement() {
        this.listItems = new ArrayList<>();
    }

    public void removeListItem(ListItem li){

        List<ListItem> old = new ArrayList<>(listItems);
        listItems = listItems.stream()
                .filter(e -> !e.equals(li))
                .collect(Collectors.toList());
        propertyChangeSupport.firePropertyChange("listItems", old, listItems);

    }

    public void addListItem(ListItem li){

        List<ListItem> old = new ArrayList<>(listItems);
        List<ListItem> newList = new ArrayList<>(listItems);
        newList.add(li);
        listItems = newList;
        propertyChangeSupport.firePropertyChange("listItems", old, listItems);

    }

    public List<ListItem> getListItems() {
        return this.listItems;
    }

}
