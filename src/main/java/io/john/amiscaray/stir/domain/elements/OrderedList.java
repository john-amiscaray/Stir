package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@HTMLElement(tagName = "ol")
@EqualsAndHashCode(callSuper = true)
@ToString
public class OrderedList extends AbstractUIElement{

    @ChildList
    @Getter
    private List<ListItem> listItems;

    @Builder
    public OrderedList(String id, @Singular List<String> cssClasses, String style, @Singular List<ListItem> listItems) {
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
