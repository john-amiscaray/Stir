package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.List;

@HTMLElement(tagName = "ol")
@EqualsAndHashCode(callSuper = true)
@ToString
public class OrderedList extends AbstractListElement {

    @Builder
    public OrderedList(String id, @Singular List<String> cssClasses, String style, @Singular List<ListItem> listItems) {
        super(id, cssClasses, style, listItems);
    }

}
