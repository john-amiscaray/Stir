package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@HTMLElement(tagName = "ul")
@AllArgsConstructor
@NoArgsConstructor
public class ListStub extends AbstractUIElement {

    @Nested
    private ListItemStub item;

}
