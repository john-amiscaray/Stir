package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@HTMLElement(tagName = "li")
@AllArgsConstructor
@EqualsAndHashCode
public class ListItemStub extends AbstractUIElement {

    @InnerContent
    private String content;

}
