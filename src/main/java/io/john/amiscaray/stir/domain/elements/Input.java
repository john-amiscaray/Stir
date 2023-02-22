package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing an input element
 */
@HTMLElement(tagName = "input", hasClosing = false)
@NoArgsConstructor
public class Input extends AbstractFormField {

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

    public Input(String id, String type, String value, String label, String name){

        this.id = id;
        this.type = type;
        this.value = value;
        this.label = label;
        this.name = name;

    }

    public Input(String type, String label, String name) {
        this.type = type;
        this.label = label;
        this.name = name;
        this.id = name;
    }

    public Input(String type, String value, String label, String accept, String alt, String autoComplete, Boolean autoFocus,
                 Boolean checked, String dirName, Boolean disabled, String form, String formAction, String formEnctype,
                 String formMethod, Boolean formNoValidate, String formTarget, Integer height, String list, Double max,
                 Integer maxLength, Double min, Integer minLength, Boolean multiple, String pattern, String placeHolder,
                 Boolean readOnly, Boolean required, Integer size, String src, Double step, Integer width, List<String> cssClasses,
                 String id, String style, String name, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, autoFocus, disabled, form, name, hidden, customAttributes);
        this.type = type;
        this.value = value;
        this.label = label;
        this.accept = accept;
        this.alt = alt;
        this.autoComplete = autoComplete;
        this.checked = checked;
        this.dirName = dirName;
        this.disabled = disabled;
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Input)) return false;
        final Input other = (Input) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$accept = this.getAccept();
        final Object other$accept = other.getAccept();
        if (this$accept == null ? other$accept != null : !this$accept.equals(other$accept)) return false;
        final Object this$alt = this.getAlt();
        final Object other$alt = other.getAlt();
        if (this$alt == null ? other$alt != null : !this$alt.equals(other$alt)) return false;
        final Object this$autoComplete = this.getAutoComplete();
        final Object other$autoComplete = other.getAutoComplete();
        if (this$autoComplete == null ? other$autoComplete != null : !this$autoComplete.equals(other$autoComplete))
            return false;
        final Object this$checked = this.getChecked();
        final Object other$checked = other.getChecked();
        if (this$checked == null ? other$checked != null : !this$checked.equals(other$checked)) return false;
        final Object this$dirName = this.getDirName();
        final Object other$dirName = other.getDirName();
        if (this$dirName == null ? other$dirName != null : !this$dirName.equals(other$dirName)) return false;
        final Object this$form = this.getForm();
        final Object other$form = other.getForm();
        if (this$form == null ? other$form != null : !this$form.equals(other$form)) return false;
        final Object this$formAction = this.getFormAction();
        final Object other$formAction = other.getFormAction();
        if (this$formAction == null ? other$formAction != null : !this$formAction.equals(other$formAction))
            return false;
        final Object this$formEnctype = this.getFormEnctype();
        final Object other$formEnctype = other.getFormEnctype();
        if (this$formEnctype == null ? other$formEnctype != null : !this$formEnctype.equals(other$formEnctype))
            return false;
        final Object this$formMethod = this.getFormMethod();
        final Object other$formMethod = other.getFormMethod();
        if (this$formMethod == null ? other$formMethod != null : !this$formMethod.equals(other$formMethod))
            return false;
        final Object this$formNoValidate = this.getFormNoValidate();
        final Object other$formNoValidate = other.getFormNoValidate();
        if (this$formNoValidate == null ? other$formNoValidate != null : !this$formNoValidate.equals(other$formNoValidate))
            return false;
        final Object this$formTarget = this.getFormTarget();
        final Object other$formTarget = other.getFormTarget();
        if (this$formTarget == null ? other$formTarget != null : !this$formTarget.equals(other$formTarget))
            return false;
        final Object this$height = this.getHeight();
        final Object other$height = other.getHeight();
        if (this$height == null ? other$height != null : !this$height.equals(other$height)) return false;
        final Object this$list = this.getList();
        final Object other$list = other.getList();
        if (this$list == null ? other$list != null : !this$list.equals(other$list)) return false;
        final Object this$max = this.getMax();
        final Object other$max = other.getMax();
        if (this$max == null ? other$max != null : !this$max.equals(other$max)) return false;
        final Object this$maxLength = this.getMaxLength();
        final Object other$maxLength = other.getMaxLength();
        if (this$maxLength == null ? other$maxLength != null : !this$maxLength.equals(other$maxLength)) return false;
        final Object this$min = this.getMin();
        final Object other$min = other.getMin();
        if (this$min == null ? other$min != null : !this$min.equals(other$min)) return false;
        final Object this$minLength = this.getMinLength();
        final Object other$minLength = other.getMinLength();
        if (this$minLength == null ? other$minLength != null : !this$minLength.equals(other$minLength)) return false;
        final Object this$multiple = this.getMultiple();
        final Object other$multiple = other.getMultiple();
        if (this$multiple == null ? other$multiple != null : !this$multiple.equals(other$multiple)) return false;
        final Object this$pattern = this.getPattern();
        final Object other$pattern = other.getPattern();
        if (this$pattern == null ? other$pattern != null : !this$pattern.equals(other$pattern)) return false;
        final Object this$placeHolder = this.getPlaceHolder();
        final Object other$placeHolder = other.getPlaceHolder();
        if (this$placeHolder == null ? other$placeHolder != null : !this$placeHolder.equals(other$placeHolder))
            return false;
        final Object this$readOnly = this.getReadOnly();
        final Object other$readOnly = other.getReadOnly();
        if (this$readOnly == null ? other$readOnly != null : !this$readOnly.equals(other$readOnly)) return false;
        final Object this$required = this.getRequired();
        final Object other$required = other.getRequired();
        if (this$required == null ? other$required != null : !this$required.equals(other$required)) return false;
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$src = this.getSrc();
        final Object other$src = other.getSrc();
        if (this$src == null ? other$src != null : !this$src.equals(other$src)) return false;
        final Object this$step = this.getStep();
        final Object other$step = other.getStep();
        if (this$step == null ? other$step != null : !this$step.equals(other$step)) return false;
        final Object this$width = this.getWidth();
        final Object other$width = other.getWidth();
        if (this$width == null ? other$width != null : !this$width.equals(other$width)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $accept = this.getAccept();
        result = result * PRIME + ($accept == null ? 43 : $accept.hashCode());
        final Object $alt = this.getAlt();
        result = result * PRIME + ($alt == null ? 43 : $alt.hashCode());
        final Object $autoComplete = this.getAutoComplete();
        result = result * PRIME + ($autoComplete == null ? 43 : $autoComplete.hashCode());
        final Object $checked = this.getChecked();
        result = result * PRIME + ($checked == null ? 43 : $checked.hashCode());
        final Object $dirName = this.getDirName();
        result = result * PRIME + ($dirName == null ? 43 : $dirName.hashCode());
        final Object $form = this.getForm();
        result = result * PRIME + ($form == null ? 43 : $form.hashCode());
        final Object $formAction = this.getFormAction();
        result = result * PRIME + ($formAction == null ? 43 : $formAction.hashCode());
        final Object $formEnctype = this.getFormEnctype();
        result = result * PRIME + ($formEnctype == null ? 43 : $formEnctype.hashCode());
        final Object $formMethod = this.getFormMethod();
        result = result * PRIME + ($formMethod == null ? 43 : $formMethod.hashCode());
        final Object $formNoValidate = this.getFormNoValidate();
        result = result * PRIME + ($formNoValidate == null ? 43 : $formNoValidate.hashCode());
        final Object $formTarget = this.getFormTarget();
        result = result * PRIME + ($formTarget == null ? 43 : $formTarget.hashCode());
        final Object $height = this.getHeight();
        result = result * PRIME + ($height == null ? 43 : $height.hashCode());
        final Object $list = this.getList();
        result = result * PRIME + ($list == null ? 43 : $list.hashCode());
        final Object $max = this.getMax();
        result = result * PRIME + ($max == null ? 43 : $max.hashCode());
        final Object $maxLength = this.getMaxLength();
        result = result * PRIME + ($maxLength == null ? 43 : $maxLength.hashCode());
        final Object $min = this.getMin();
        result = result * PRIME + ($min == null ? 43 : $min.hashCode());
        final Object $minLength = this.getMinLength();
        result = result * PRIME + ($minLength == null ? 43 : $minLength.hashCode());
        final Object $multiple = this.getMultiple();
        result = result * PRIME + ($multiple == null ? 43 : $multiple.hashCode());
        final Object $pattern = this.getPattern();
        result = result * PRIME + ($pattern == null ? 43 : $pattern.hashCode());
        final Object $placeHolder = this.getPlaceHolder();
        result = result * PRIME + ($placeHolder == null ? 43 : $placeHolder.hashCode());
        final Object $readOnly = this.getReadOnly();
        result = result * PRIME + ($readOnly == null ? 43 : $readOnly.hashCode());
        final Object $required = this.getRequired();
        result = result * PRIME + ($required == null ? 43 : $required.hashCode());
        final Object $size = this.getSize();
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $src = this.getSrc();
        result = result * PRIME + ($src == null ? 43 : $src.hashCode());
        final Object $step = this.getStep();
        result = result * PRIME + ($step == null ? 43 : $step.hashCode());
        final Object $width = this.getWidth();
        result = result * PRIME + ($width == null ? 43 : $width.hashCode());
        return result;
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
        private ArrayList<String> cssClasses;
        private String id;
        private String style;
        private String name;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

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

        public InputBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public InputBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public InputBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public InputBuilder id(String id) {
            this.id = id;
            return this;
        }

        public InputBuilder style(String style) {
            this.style = style;
            return this;
        }

        public InputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public InputBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public InputBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public InputBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            for (final Map.Entry<? extends String, ? extends String> $lombokEntry : customAttributes.entrySet()) {
                this.customAttributes$key.add($lombokEntry.getKey());
                this.customAttributes$value.add($lombokEntry.getValue());
            }
            return this;
        }

        public InputBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Input build() {
            List<String> cssClasses;
            switch (this.cssClasses == null ? 0 : this.cssClasses.size()) {
                case 0:
                    cssClasses = java.util.Collections.emptyList();
                    break;
                case 1:
                    cssClasses = java.util.Collections.singletonList(this.cssClasses.get(0));
                    break;
                default:
                    cssClasses = java.util.Collections.unmodifiableList(new ArrayList<String>(this.cssClasses));
            }
            Map<String, String> customAttributes;
            switch (this.customAttributes$key == null ? 0 : this.customAttributes$key.size()) {
                case 0:
                    customAttributes = java.util.Collections.emptyMap();
                    break;
                case 1:
                    customAttributes = java.util.Collections.singletonMap(this.customAttributes$key.get(0), this.customAttributes$value.get(0));
                    break;
                default:
                    customAttributes = new java.util.LinkedHashMap<String, String>(this.customAttributes$key.size() < 1073741824 ? 1 + this.customAttributes$key.size() + (this.customAttributes$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.customAttributes$key.size(); $i++)
                        customAttributes.put(this.customAttributes$key.get($i), (String) this.customAttributes$value.get($i));
                    customAttributes = java.util.Collections.unmodifiableMap(customAttributes);
            }

            return new Input(type, value, label, accept, alt, autoComplete, autoFocus, checked, dirName, disabled, form, formAction, formEnctype, formMethod, formNoValidate, formTarget, height, list, max, maxLength, min, minLength, multiple, pattern, placeHolder, readOnly, required, size, src, step, width, cssClasses, id, style, name, hidden, customAttributes);
        }

        public String toString() {
            return "Input.InputBuilder(type=" + this.type + ", value=" + this.value + ", label=" + this.label + ", accept=" + this.accept + ", alt=" + this.alt + ", autoComplete=" + this.autoComplete + ", autoFocus=" + this.autoFocus + ", checked=" + this.checked + ", dirName=" + this.dirName + ", disabled=" + this.disabled + ", form=" + this.form + ", formAction=" + this.formAction + ", formEnctype=" + this.formEnctype + ", formMethod=" + this.formMethod + ", formNoValidate=" + this.formNoValidate + ", formTarget=" + this.formTarget + ", height=" + this.height + ", list=" + this.list + ", max=" + this.max + ", maxLength=" + this.maxLength + ", min=" + this.min + ", minLength=" + this.minLength + ", multiple=" + this.multiple + ", pattern=" + this.pattern + ", placeHolder=" + this.placeHolder + ", readOnly=" + this.readOnly + ", required=" + this.required + ", size=" + this.size + ", src=" + this.src + ", step=" + this.step + ", width=" + this.width + ", cssClasses=" + this.cssClasses + ", id=" + this.id + ", style=" + this.style + ", name=" + this.name + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
