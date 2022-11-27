package io.john.amiscaray.espresso.stub;

import io.john.amiscaray.espresso.annotation.Attribute;
import io.john.amiscaray.espresso.annotation.HTMLElement;

@HTMLElement(tagName = "form")
public class SimpleForm {

    @Attribute(name = "action", defaultValue = "/")
    private String action;

    @Attribute(name = "method", defaultValue = "post")
    private String method;

}
