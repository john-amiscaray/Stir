package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "select")
@AllArgsConstructor
@RequiredArgsConstructor
public class Select extends AbstractUIElement {

    @Attribute(name="autofocus")
    @Getter
    private Boolean autoFocus;

    @Attribute(name="disabled")
    @Getter
    private Boolean disabled;

    @Attribute(name="form")
    @Getter
    private String form;

    @Attribute(name="multiple")
    @Getter
    private Boolean multiple;

    @Attribute(name="name")
    @Getter
    private final String name;

    @Attribute(name="required")
    @Getter
    private Boolean required;

    @Attribute(name="size")
    @Getter
    private Integer size;

    @ChildList
    @Getter
    private List<Option> options = new ArrayList<>();

    @Label(defaultText = "select")
    private String label;

    @Builder
    public Select(String id, @Singular List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled, String form,
                  Boolean multiple, String name, Boolean required, Integer size, @Singular List<Option> options, String label) {
        super(id, cssClasses, style);
        this.autoFocus = autoFocus;
        this.label = label;
        this.disabled = disabled;
        this.form = form;
        this.multiple = multiple;
        this.name = name;
        this.required = required;
        this.size = size;
        this.options = options;
    }

    public void setAutoFocus(Boolean autoFocus) {
        propertyChangeSupport.firePropertyChange("autoFocus", this.autoFocus, autoFocus);
        this.autoFocus = autoFocus;
    }

    public void setDisabled(Boolean disabled) {
        propertyChangeSupport.firePropertyChange("disabled", this.disabled, disabled);
        this.disabled = disabled;
    }

    public void setForm(String form) {
        propertyChangeSupport.firePropertyChange("form", this.form, form);
        this.form = form;
    }

    public void setMultiple(Boolean multiple) {
        propertyChangeSupport.firePropertyChange("multiple", this.multiple, multiple);
        this.multiple = multiple;
    }

    public void setRequired(Boolean required) {
        propertyChangeSupport.firePropertyChange("required", this.required, required);
        this.required = required;
    }

    public void setSize(Integer size) {
        propertyChangeSupport.firePropertyChange("size", this.size, size);
        this.size = size;
    }

    public void setOptions(List<Option> options) {
        propertyChangeSupport.firePropertyChange("options", this.options, options);
        this.options = options;
    }

    public void addOption(Option option) {

        List<Option> old = (List<Option>) ((ArrayList<Option>) options).clone();
        options.add(option);
        propertyChangeSupport.firePropertyChange("options", old, options);

    }

}
