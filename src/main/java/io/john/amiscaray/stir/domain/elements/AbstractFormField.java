package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A parent class for common form field attributes
 */
@NoArgsConstructor
public abstract class AbstractFormField extends AbstractUIElement{

    /**
     * The autofocus attribute of the element
     */
    @Attribute(name = "autofocus")
    protected Boolean autoFocus;

    /**
     * The disabled attribute of the element
     */
    @Attribute(name = "disabled")
    protected Boolean disabled;

    /**
     * The form attribute of the element
     */
    @Attribute(name = "form")
    protected String form;

    /**
     * The name attribute of the element
     */
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
