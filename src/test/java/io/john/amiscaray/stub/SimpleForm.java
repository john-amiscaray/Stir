package io.john.amiscaray.stub;

import io.john.amiscaray.annotation.Attribute;
import io.john.amiscaray.annotation.HTMLElement;

@HTMLElement(tagName = "form")
public class SimpleForm {

    @Attribute(name = "action", defaultValue = "/")
    private String action;

    @Attribute(name = "method", defaultValue = "post")
    private String method;

}
