package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.ListItem;
import lombok.NoArgsConstructor;

@HTMLElement(tagName = "div")
@NoArgsConstructor
public class ElementWithBadAttribute extends AbstractUIElement {

    @Attribute(name="thing")
    private ListItem item;

}
