package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A parent class for common form field attributes
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    public AbstractFormField(String id, List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled, String form, String name, boolean hidden) {
        super(id, cssClasses, style, hidden);
        this.autoFocus = autoFocus;
        this.disabled = disabled;
        this.form = form;
        this.name = name;
    }

}
