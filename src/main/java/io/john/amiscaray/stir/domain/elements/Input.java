package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;

@HTMLElement(tagName = "input", hasClosing = false)
public class Input extends AbstractUIElement {

    @Attribute(name="type", defaultValue="text")
    private final String type;

    @Attribute(name="value", defaultValue="")
    private String value;

    @Label(defaultText="")
    private final String label;

    @Attribute(name="accept")
    private String accept;

    @Attribute(name="alt")
    private String alt;

    @Attribute(name="autocomplete")
    private String autoComplete;

    @Attribute(name="autofocus")
    private Boolean autoFocus;

    @Attribute(name="checked")
    private Boolean checked;

    @Attribute(name="dirname")
    private String dirName;

    @Attribute(name="disabled")
    private Boolean disabled;

    @Attribute(name="form")
    private String form;

    @Attribute(name="formaction")
    private String formAction;

    @Attribute(name="formenctype")
    private String formEnctype;

    @Attribute(name="formmethod")
    private String formMethod;

    @Attribute(name="formnovalidate")
    private Boolean formNoValidate;

    @Attribute(name="formtarget")
    private String formTarget;

    @Attribute(name="height")
    private Integer height;

    @Attribute(name="list")
    private String list;

    @Attribute(name="max")
    private Double max;

    @Attribute(name="maxlength")
    private Integer maxLength;

    @Attribute(name="min")
    private Double min;

    @Attribute(name="minlength")
    private Integer minLength;

    @Attribute(name="multiple")
    private Boolean multiple;

    @Attribute(name="pattern")
    private String pattern;

    @Attribute(name="placeholder")
    private String placeHolder;

    @Attribute(name="readonly")
    private Boolean readOnly;

    @Attribute(name="required")
    private Boolean required;

    @Attribute(name="size")
    private Integer size;

    @Attribute(name="src")
    private String src;

    @Attribute(name="step")
    private Double step;

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
    
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Input)) return false;
        final Input other = (Input) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$value = this.value;
        final Object other$value = other.value;
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$label = this.label;
        final Object other$label = other.label;
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$accept = this.accept;
        final Object other$accept = other.accept;
        if (this$accept == null ? other$accept != null : !this$accept.equals(other$accept)) return false;
        final Object this$alt = this.alt;
        final Object other$alt = other.alt;
        if (this$alt == null ? other$alt != null : !this$alt.equals(other$alt)) return false;
        final Object this$autoComplete = this.autoComplete;
        final Object other$autoComplete = other.autoComplete;
        if (this$autoComplete == null ? other$autoComplete != null : !this$autoComplete.equals(other$autoComplete))
            return false;
        final Object this$autoFocus = this.autoFocus;
        final Object other$autoFocus = other.autoFocus;
        if (this$autoFocus == null ? other$autoFocus != null : !this$autoFocus.equals(other$autoFocus)) return false;
        final Object this$checked = this.checked;
        final Object other$checked = other.checked;
        if (this$checked == null ? other$checked != null : !this$checked.equals(other$checked)) return false;
        final Object this$dirName = this.dirName;
        final Object other$dirName = other.dirName;
        if (this$dirName == null ? other$dirName != null : !this$dirName.equals(other$dirName)) return false;
        final Object this$disabled = this.disabled;
        final Object other$disabled = other.disabled;
        if (this$disabled == null ? other$disabled != null : !this$disabled.equals(other$disabled)) return false;
        final Object this$form = this.form;
        final Object other$form = other.form;
        if (this$form == null ? other$form != null : !this$form.equals(other$form)) return false;
        final Object this$formAction = this.formAction;
        final Object other$formAction = other.formAction;
        if (this$formAction == null ? other$formAction != null : !this$formAction.equals(other$formAction))
            return false;
        final Object this$formEnctype = this.formEnctype;
        final Object other$formEnctype = other.formEnctype;
        if (this$formEnctype == null ? other$formEnctype != null : !this$formEnctype.equals(other$formEnctype))
            return false;
        final Object this$formMethod = this.formMethod;
        final Object other$formMethod = other.formMethod;
        if (this$formMethod == null ? other$formMethod != null : !this$formMethod.equals(other$formMethod))
            return false;
        final Object this$formNoValidate = this.formNoValidate;
        final Object other$formNoValidate = other.formNoValidate;
        if (this$formNoValidate == null ? other$formNoValidate != null : !this$formNoValidate.equals(other$formNoValidate))
            return false;
        final Object this$formTarget = this.formTarget;
        final Object other$formTarget = other.formTarget;
        if (this$formTarget == null ? other$formTarget != null : !this$formTarget.equals(other$formTarget))
            return false;
        final Object this$height = this.height;
        final Object other$height = other.height;
        if (this$height == null ? other$height != null : !this$height.equals(other$height)) return false;
        final Object this$list = this.list;
        final Object other$list = other.list;
        if (this$list == null ? other$list != null : !this$list.equals(other$list)) return false;
        final Object this$max = this.max;
        final Object other$max = other.max;
        if (this$max == null ? other$max != null : !this$max.equals(other$max)) return false;
        final Object this$maxLength = this.maxLength;
        final Object other$maxLength = other.maxLength;
        if (this$maxLength == null ? other$maxLength != null : !this$maxLength.equals(other$maxLength)) return false;
        final Object this$min = this.min;
        final Object other$min = other.min;
        if (this$min == null ? other$min != null : !this$min.equals(other$min)) return false;
        final Object this$minLength = this.minLength;
        final Object other$minLength = other.minLength;
        if (this$minLength == null ? other$minLength != null : !this$minLength.equals(other$minLength)) return false;
        final Object this$multiple = this.multiple;
        final Object other$multiple = other.multiple;
        if (this$multiple == null ? other$multiple != null : !this$multiple.equals(other$multiple)) return false;
        final Object this$pattern = this.pattern;
        final Object other$pattern = other.pattern;
        if (this$pattern == null ? other$pattern != null : !this$pattern.equals(other$pattern)) return false;
        final Object this$placeHolder = this.placeHolder;
        final Object other$placeHolder = other.placeHolder;
        if (this$placeHolder == null ? other$placeHolder != null : !this$placeHolder.equals(other$placeHolder))
            return false;
        final Object this$readOnly = this.readOnly;
        final Object other$readOnly = other.readOnly;
        if (this$readOnly == null ? other$readOnly != null : !this$readOnly.equals(other$readOnly)) return false;
        final Object this$required = this.required;
        final Object other$required = other.required;
        if (this$required == null ? other$required != null : !this$required.equals(other$required)) return false;
        final Object this$size = this.size;
        final Object other$size = other.size;
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$src = this.src;
        final Object other$src = other.src;
        if (this$src == null ? other$src != null : !this$src.equals(other$src)) return false;
        final Object this$step = this.step;
        final Object other$step = other.step;
        if (this$step == null ? other$step != null : !this$step.equals(other$step)) return false;
        final Object this$width = this.width;
        final Object other$width = other.width;
        if (this$width == null ? other$width != null : !this$width.equals(other$width)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Input;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $value = this.value;
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $label = this.label;
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $accept = this.accept;
        result = result * PRIME + ($accept == null ? 43 : $accept.hashCode());
        final Object $alt = this.alt;
        result = result * PRIME + ($alt == null ? 43 : $alt.hashCode());
        final Object $autoComplete = this.autoComplete;
        result = result * PRIME + ($autoComplete == null ? 43 : $autoComplete.hashCode());
        final Object $autoFocus = this.autoFocus;
        result = result * PRIME + ($autoFocus == null ? 43 : $autoFocus.hashCode());
        final Object $checked = this.checked;
        result = result * PRIME + ($checked == null ? 43 : $checked.hashCode());
        final Object $dirName = this.dirName;
        result = result * PRIME + ($dirName == null ? 43 : $dirName.hashCode());
        final Object $disabled = this.disabled;
        result = result * PRIME + ($disabled == null ? 43 : $disabled.hashCode());
        final Object $form = this.form;
        result = result * PRIME + ($form == null ? 43 : $form.hashCode());
        final Object $formAction = this.formAction;
        result = result * PRIME + ($formAction == null ? 43 : $formAction.hashCode());
        final Object $formEnctype = this.formEnctype;
        result = result * PRIME + ($formEnctype == null ? 43 : $formEnctype.hashCode());
        final Object $formMethod = this.formMethod;
        result = result * PRIME + ($formMethod == null ? 43 : $formMethod.hashCode());
        final Object $formNoValidate = this.formNoValidate;
        result = result * PRIME + ($formNoValidate == null ? 43 : $formNoValidate.hashCode());
        final Object $formTarget = this.formTarget;
        result = result * PRIME + ($formTarget == null ? 43 : $formTarget.hashCode());
        final Object $height = this.height;
        result = result * PRIME + ($height == null ? 43 : $height.hashCode());
        final Object $list = this.list;
        result = result * PRIME + ($list == null ? 43 : $list.hashCode());
        final Object $max = this.max;
        result = result * PRIME + ($max == null ? 43 : $max.hashCode());
        final Object $maxLength = this.maxLength;
        result = result * PRIME + ($maxLength == null ? 43 : $maxLength.hashCode());
        final Object $min = this.min;
        result = result * PRIME + ($min == null ? 43 : $min.hashCode());
        final Object $minLength = this.minLength;
        result = result * PRIME + ($minLength == null ? 43 : $minLength.hashCode());
        final Object $multiple = this.multiple;
        result = result * PRIME + ($multiple == null ? 43 : $multiple.hashCode());
        final Object $pattern = this.pattern;
        result = result * PRIME + ($pattern == null ? 43 : $pattern.hashCode());
        final Object $placeHolder = this.placeHolder;
        result = result * PRIME + ($placeHolder == null ? 43 : $placeHolder.hashCode());
        final Object $readOnly = this.readOnly;
        result = result * PRIME + ($readOnly == null ? 43 : $readOnly.hashCode());
        final Object $required = this.required;
        result = result * PRIME + ($required == null ? 43 : $required.hashCode());
        final Object $size = this.size;
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $src = this.src;
        result = result * PRIME + ($src == null ? 43 : $src.hashCode());
        final Object $step = this.step;
        result = result * PRIME + ($step == null ? 43 : $step.hashCode());
        final Object $width = this.width;
        result = result * PRIME + ($width == null ? 43 : $width.hashCode());
        return result;
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
