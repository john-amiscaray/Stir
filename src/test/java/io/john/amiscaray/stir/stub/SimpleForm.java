package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;

@HTMLElement(tagName = "form")
public class SimpleForm extends AbstractUIElement {

    @Attribute(name = "action", defaultValue = "/")
    private String action;

    @Attribute(name = "method", defaultValue = "post")
    private String method;

}
