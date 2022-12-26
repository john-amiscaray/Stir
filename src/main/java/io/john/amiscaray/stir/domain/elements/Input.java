package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "input", hasClosing = false)
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Input extends AbstractUIElement {

    @Attribute(name="type", defaultValue="text")
    @Getter
    private final String type;

    @Attribute(name="value", defaultValue="")
    @Getter
    private String value;

    @Label(defaultText="")
    @Getter
    private final String label;

    @Attribute(name="accept")
    @Getter
    private String accept;

    @Attribute(name="alt")
    @Getter
    private String alt;

    @Attribute(name="autocomplete")
    @Getter
    private String autoComplete;

    @Attribute(name="autofocus")
    @Getter
    private Boolean autoFocus;

    @Attribute(name="checked")
    @Getter
    private Boolean checked;

    @Attribute(name="dirname")
    @Getter
    private String dirName;

    @Attribute(name="disabled")
    @Getter
    private Boolean disabled;

    @Attribute(name="form")
    @Getter
    private String form;

    @Attribute(name="formaction")
    @Getter
    private String formAction;

    @Attribute(name="formenctype")
    @Getter
    private String formEnctype;

    @Attribute(name="formmethod")
    @Getter
    private String formMethod;

    @Attribute(name="formnovalidate")
    @Getter
    private Boolean formNoValidate;

    @Attribute(name="formtarget")
    @Getter
    private String formTarget;

    @Attribute(name="height")
    @Getter
    private Integer height;

    @Attribute(name="list")
    @Getter
    private String list;

    @Attribute(name="max")
    @Getter
    private Double max;

    @Attribute(name="maxlength")
    @Getter
    private Integer maxLength;

    @Attribute(name="min")
    @Getter
    private Double min;

    @Attribute(name="minlength")
    @Getter
    private Integer minLength;

    @Attribute(name="multiple")
    @Getter
    private Boolean multiple;

    @Attribute(name="pattern")
    @Getter
    private String pattern;

    @Attribute(name="placeholder")
    @Getter
    private String placeHolder;

    @Attribute(name="readonly")
    @Getter
    private Boolean readOnly;

    @Attribute(name="required")
    @Getter
    private Boolean required;

    @Attribute(name="size")
    @Getter
    private Integer size;

    @Attribute(name="src")
    @Getter
    private String src;

    @Attribute(name="step")
    @Getter
    private Double step;

    @Attribute(name="width")
    @Getter
    private Integer width;

    public Input(String id, String type, String value, String label){

        this.id = id;
        this.type = type;
        this.value = value;
        this.label = label;

    }

    public void setValue(String value) {
        propertyChangeSupport.firePropertyChange("value", this.value, value);
        this.value = value;
    }

    public void setAccept(String accept) {
        propertyChangeSupport.firePropertyChange("accept", this.accept, accept);
        this.accept = accept;
    }

    public void setAlt(String alt) {
        propertyChangeSupport.firePropertyChange("alt", this.alt, alt);
        this.alt = alt;
    }

    public void setAutoComplete(String autoComplete) {
        propertyChangeSupport.firePropertyChange("autoComplete", this.autoComplete, autoComplete);
        this.autoComplete = autoComplete;
    }

    public void setAutoFocus(Boolean autoFocus) {
        propertyChangeSupport.firePropertyChange("autoFocus", this.autoFocus, autoFocus);
        this.autoFocus = autoFocus;
    }

    public void setChecked(Boolean checked) {
        propertyChangeSupport.firePropertyChange("checked", this.checked, checked);
        this.checked = checked;
    }

    public void setDirName(String dirName) {
        propertyChangeSupport.firePropertyChange("dirName", this.dirName, dirName);
        this.dirName = dirName;
    }

    public void setDisabled(Boolean disabled) {
        propertyChangeSupport.firePropertyChange("disabled", this.disabled, disabled);
        this.disabled = disabled;
    }

    public void setForm(String form) {
        propertyChangeSupport.firePropertyChange("form", this.form, form);
        this.form = form;
    }

    public void setFormAction(String formAction) {
        propertyChangeSupport.firePropertyChange("formAction", this.formAction, formAction);
        this.formAction = formAction;
    }

    public void setFormEnctype(String formEnctype) {
        propertyChangeSupport.firePropertyChange("formEnctype", this.formEnctype, formEnctype);
        this.formEnctype = formEnctype;
    }

    public void setFormMethod(String formMethod) {
        propertyChangeSupport.firePropertyChange("formMethod", this.formMethod, formMethod);
        this.formMethod = formMethod;
    }

    public void setFormNoValidate(Boolean formNoValidate) {
        propertyChangeSupport.firePropertyChange("formNoValidate", this.formNoValidate, formNoValidate);
        this.formNoValidate = formNoValidate;
    }

    public void setFormTarget(String formTarget) {
        propertyChangeSupport.firePropertyChange("formTarget", this.formTarget, formTarget);
        this.formTarget = formTarget;
    }

    public void setHeight(Integer height) {
        propertyChangeSupport.firePropertyChange("height", this.height, height);
        this.height = height;
    }

    public void setList(String list) {
        propertyChangeSupport.firePropertyChange("list", this.list, list);
        this.list = list;
    }

    public void setMax(Double max) {
        propertyChangeSupport.firePropertyChange("max", this.max, max);
        this.max = max;
    }

    public void setMaxLength(Integer maxLength) {
        propertyChangeSupport.firePropertyChange("maxLength", this.maxLength, maxLength);
        this.maxLength = maxLength;
    }

    public void setMin(Double min) {
        propertyChangeSupport.firePropertyChange("min", this.min, min);
        this.min = min;
    }

    public void setMinLength(Integer minLength) {
        propertyChangeSupport.firePropertyChange("minLength", this.minLength, minLength);
        this.minLength = minLength;
    }

    public void setMultiple(Boolean multiple) {
        propertyChangeSupport.firePropertyChange("multiple", this.multiple, multiple);
        this.multiple = multiple;
    }

    public void setPattern(String pattern) {
        propertyChangeSupport.firePropertyChange("pattern", this.pattern, pattern);
        this.pattern = pattern;
    }

    public void setPlaceHolder(String placeHolder) {
        propertyChangeSupport.firePropertyChange("placeHolder", this.placeHolder, placeHolder);
        this.placeHolder = placeHolder;
    }

    public void setReadOnly(Boolean readOnly) {
        propertyChangeSupport.firePropertyChange("readOnly", this.readOnly, readOnly);
        this.readOnly = readOnly;
    }

    public void setRequired(Boolean required) {
        propertyChangeSupport.firePropertyChange("required", this.required, required);
        this.required = required;
    }

    public void setSize(Integer size) {
        propertyChangeSupport.firePropertyChange("size", this.size, size);
        this.size = size;
    }

    public void setSrc(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public void setStep(Double step) {
        propertyChangeSupport.firePropertyChange("step", this.step, step);
        this.step = step;
    }

    public void setWidth(Integer width) {
        propertyChangeSupport.firePropertyChange("width", this.width, width);
        this.width = width;
    }

}
