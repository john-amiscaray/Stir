package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractListElement extends AbstractUIElement{

    @ChildList
    @Getter
    protected List<ListItem> listItems;

    public AbstractListElement(String id, List<String> cssClasses, String style, List<ListItem> listItems) {
        super(id, cssClasses, style);
        this.listItems = listItems;
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

}
