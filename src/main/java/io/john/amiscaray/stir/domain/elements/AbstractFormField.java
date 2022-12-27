package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;

import java.util.List;

public abstract class AbstractFormField extends AbstractUIElement{

    @Attribute(name = "autofocus")
    protected Boolean autoFocus;

    @Attribute(name = "disabled")
    protected Boolean disabled;

    @Attribute(name = "form")
    protected String form;

    @Attribute(name = "name")
    protected String name;

    public AbstractFormField(String id, List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled, String form, String name) {
        super(id, cssClasses, style);
        this.autoFocus = autoFocus;
        this.disabled = disabled;
        this.form = form;
        this.name = name;
    }

}
