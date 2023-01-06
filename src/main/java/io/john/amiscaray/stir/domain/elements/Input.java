package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A pojo representing an input element
 */
@HTMLElement(tagName = "input", hasClosing = false)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Input extends AbstractUIElement {

    /**
     * The type attribute of the input
     */
    @Attribute(name="type", defaultValue="text")
    private String type;

    /**
     * The value attribute of the input
     */
    @Attribute(name="value", defaultValue="")
    private String value;

    /**
     * The label for the input
     */
    @Label(defaultText="")
    private String label;

    /**
     * The accept attribute of the input
     */
    @Attribute(name="accept")
    private String accept;

    /**
     * The alt attribute of the input
     */
    @Attribute(name="alt")
    private String alt;

    /**
     * The autocomplete attribute of the input
     */
    @Attribute(name="autocomplete")
    private String autoComplete;

    /**
     * The autofocus attribute of the input
     */
    @Attribute(name="autofocus")
    private Boolean autoFocus;

    /**
     * The checked attribute of the input
     */
    @Attribute(name="checked")
    private Boolean checked;

    /**
     * The dirname attribute of the input
     */
    @Attribute(name="dirname")
    private String dirName;

    /**
     * The disabled attribute of the input
     */
    @Attribute(name="disabled")
    private Boolean disabled;

    /**
     * The form attribute of the input
     */
    @Attribute(name="form")
    private String form;

    /**
     * The formaction attribute of the input
     */
    @Attribute(name="formaction")
    private String formAction;

    /**
     * The formenctype attribute of the input
     */
    @Attribute(name="formenctype")
    private String formEnctype;

    /**
     * The formmethod attribute of the input
     */
    @Attribute(name="formmethod")
    private String formMethod;

    /**
     * The formnovalidate attribute of the input
     */
    @Attribute(name="formnovalidate")
    private Boolean formNoValidate;

    /**
     * The formtarget attribute of the input
     */
    @Attribute(name="formtarget")
    private String formTarget;

    /**
     * The height attribute of the input
     */
    @Attribute(name="height")
    private Integer height;

    /**
     * The list attribute of the input
     */
    @Attribute(name="list")
    private String list;

    /**
     * The max attribute of the input
     */
    @Attribute(name="max")
    private Double max;

    /**
     * The maxlength attribute of the input
     */
    @Attribute(name="maxlength")
    private Integer maxLength;

    /**
     * The min attribute of the input
     */
    @Attribute(name="min")
    private Double min;

    /**
     * The minlength attribute of the input
     */
    @Attribute(name="minlength")
    private Integer minLength;

    /**
     * The multiple attribute of the input
     */
    @Attribute(name="multiple")
    private Boolean multiple;

    /**
     * The pattern attribute of the input
     */
    @Attribute(name="pattern")
    private String pattern;

    /**
     * The placeholder attribute of the input
     */
    @Attribute(name="placeholder")
    private String placeHolder;

    /**
     * The readonly attribute of the input
     */
    @Attribute(name="readonly")
    private Boolean readOnly;

    /**
     * The required attribute of the input
     */
    @Attribute(name="required")
    private Boolean required;

    /**
     * The size attribute of the input
     */
    @Attribute(name="size")
    private Integer size;

    /**
     * The src attribute of the input
     */
    @Attribute(name="src")
    private String src;

    /**
     * The step attribute of the input
     */
    @Attribute(name="step")
    private Double step;

    /**
     * The width attribute of the input
     */
    @Attribute(name="width")
    private Integer width;

    public Input(String id, String type, String value, String label){

        this.id = id;
        this.type = type;
        this.value = value;
        this.label = label;

    }

    public Input(String type, String label) {
        this.type = type;
        this.label = label;
    }

    public Input(String type, String value, String label, String accept, String alt, String autoComplete, Boolean autoFocus, Boolean checked, String dirName, Boolean disabled, String form, String formAction, String formEnctype, String formMethod, Boolean formNoValidate, String formTarget, Integer height, String list, Double max, Integer maxLength, Double min, Integer minLength, Boolean multiple, String pattern, String placeHolder, Boolean readOnly, Boolean required, Integer size, String src, Double step, Integer width) {
        this.type = type;
        this.value = value;
        this.label = label;
        this.accept = accept;
        this.alt = alt;
        this.autoComplete = autoComplete;
        this.autoFocus = autoFocus;
        this.checked = checked;
        this.dirName = dirName;
        this.disabled = disabled;
        this.form = form;
        this.formAction = formAction;
        this.formEnctype = formEnctype;
        this.formMethod = formMethod;
        this.formNoValidate = formNoValidate;
        this.formTarget = formTarget;
        this.height = height;
        this.list = list;
        this.max = max;
        this.maxLength = maxLength;
        this.min = min;
        this.minLength = minLength;
        this.multiple = multiple;
        this.pattern = pattern;
        this.placeHolder = placeHolder;
        this.readOnly = readOnly;
        this.required = required;
        this.size = size;
        this.src = src;
        this.step = step;
        this.width = width;
    }

    public static InputBuilder builder() {
        return new InputBuilder();
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

    protected boolean canEqual(final Object other) {
        return other instanceof Input;
    }

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    public String getAccept() {
        return this.accept;
    }

    public String getAlt() {
        return this.alt;
    }

    public String getAutoComplete() {
        return this.autoComplete;
    }

    public Boolean getAutoFocus() {
        return this.autoFocus;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public String getDirName() {
        return this.dirName;
    }

    public Boolean getDisabled() {
        return this.disabled;
    }

    public String getForm() {
        return this.form;
    }

    public String getFormAction() {
        return this.formAction;
    }

    public String getFormEnctype() {
        return this.formEnctype;
    }

    public String getFormMethod() {
        return this.formMethod;
    }

    public Boolean getFormNoValidate() {
        return this.formNoValidate;
    }

    public String getFormTarget() {
        return this.formTarget;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getList() {
        return this.list;
    }

    public Double getMax() {
        return this.max;
    }

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public Double getMin() {
        return this.min;
    }

    public Integer getMinLength() {
        return this.minLength;
    }

    public Boolean getMultiple() {
        return this.multiple;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String getPlaceHolder() {
        return this.placeHolder;
    }

    public Boolean getReadOnly() {
        return this.readOnly;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public Integer getSize() {
        return this.size;
    }

    public String getSrc() {
        return this.src;
    }

    public Double getStep() {
        return this.step;
    }

    public Integer getWidth() {
        return this.width;
    }

    public static class InputBuilder {
        private String type;
        private String value;
        private String label;
        private String accept;
        private String alt;
        private String autoComplete;
        private Boolean autoFocus;
        private Boolean checked;
        private String dirName;
        private Boolean disabled;
        private String form;
        private String formAction;
        private String formEnctype;
        private String formMethod;
        private Boolean formNoValidate;
        private String formTarget;
        private Integer height;
        private String list;
        private Double max;
        private Integer maxLength;
        private Double min;
        private Integer minLength;
        private Boolean multiple;
        private String pattern;
        private String placeHolder;
        private Boolean readOnly;
        private Boolean required;
        private Integer size;
        private String src;
        private Double step;
        private Integer width;

        InputBuilder() {
        }

        public InputBuilder type(String type) {
            this.type = type;
            return this;
        }

        public InputBuilder value(String value) {
            this.value = value;
            return this;
        }

        public InputBuilder label(String label) {
            this.label = label;
            return this;
        }

        public InputBuilder accept(String accept) {
            this.accept = accept;
            return this;
        }

        public InputBuilder alt(String alt) {
            this.alt = alt;
            return this;
        }

        public InputBuilder autoComplete(String autoComplete) {
            this.autoComplete = autoComplete;
            return this;
        }

        public InputBuilder autoFocus(Boolean autoFocus) {
            this.autoFocus = autoFocus;
            return this;
        }

        public InputBuilder checked(Boolean checked) {
            this.checked = checked;
            return this;
        }

        public InputBuilder dirName(String dirName) {
            this.dirName = dirName;
            return this;
        }

        public InputBuilder disabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public InputBuilder form(String form) {
            this.form = form;
            return this;
        }

        public InputBuilder formAction(String formAction) {
            this.formAction = formAction;
            return this;
        }

        public InputBuilder formEnctype(String formEnctype) {
            this.formEnctype = formEnctype;
            return this;
        }

        public InputBuilder formMethod(String formMethod) {
            this.formMethod = formMethod;
            return this;
        }

        public InputBuilder formNoValidate(Boolean formNoValidate) {
            this.formNoValidate = formNoValidate;
            return this;
        }

        public InputBuilder formTarget(String formTarget) {
            this.formTarget = formTarget;
            return this;
        }

        public InputBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public InputBuilder list(String list) {
            this.list = list;
            return this;
        }

        public InputBuilder max(Double max) {
            this.max = max;
            return this;
        }

        public InputBuilder maxLength(Integer maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public InputBuilder min(Double min) {
            this.min = min;
            return this;
        }

        public InputBuilder minLength(Integer minLength) {
            this.minLength = minLength;
            return this;
        }

        public InputBuilder multiple(Boolean multiple) {
            this.multiple = multiple;
            return this;
        }

        public InputBuilder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public InputBuilder placeHolder(String placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public InputBuilder readOnly(Boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public InputBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public InputBuilder size(Integer size) {
            this.size = size;
            return this;
        }

        public InputBuilder src(String src) {
            this.src = src;
            return this;
        }

        public InputBuilder step(Double step) {
            this.step = step;
            return this;
        }

        public InputBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public Input build() {
            return new Input(type, value, label, accept, alt, autoComplete, autoFocus, checked, dirName, disabled, form, formAction, formEnctype, formMethod, formNoValidate, formTarget, height, list, max, maxLength, min, minLength, multiple, pattern, placeHolder, readOnly, required, size, src, step, width);
        }

        public String toString() {
            return "Input.InputBuilder(type=" + this.type + ", value=" + this.value + ", label=" + this.label + ", accept=" + this.accept + ", alt=" + this.alt + ", autoComplete=" + this.autoComplete + ", autoFocus=" + this.autoFocus + ", checked=" + this.checked + ", dirName=" + this.dirName + ", disabled=" + this.disabled + ", form=" + this.form + ", formAction=" + this.formAction + ", formEnctype=" + this.formEnctype + ", formMethod=" + this.formMethod + ", formNoValidate=" + this.formNoValidate + ", formTarget=" + this.formTarget + ", height=" + this.height + ", list=" + this.list + ", max=" + this.max + ", maxLength=" + this.maxLength + ", min=" + this.min + ", minLength=" + this.minLength + ", multiple=" + this.multiple + ", pattern=" + this.pattern + ", placeHolder=" + this.placeHolder + ", readOnly=" + this.readOnly + ", required=" + this.required + ", size=" + this.size + ", src=" + this.src + ", step=" + this.step + ", width=" + this.width + ")";
        }
    }
}
