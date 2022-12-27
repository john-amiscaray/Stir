package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "ul")
public class UnorderedList extends AbstractListElement{

    @Builder
    public UnorderedList(String id, @Singular List<String> cssClasses, String style, @Singular List<ListItem> listItems) {
        super(id, cssClasses, style, listItems);
    }

}
