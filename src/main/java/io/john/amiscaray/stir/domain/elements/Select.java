package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@HTMLElement(tagName = "select")
public class Select extends AbstractFormField {

    @Attribute(name="multiple")
    private Boolean multiple;

    @Attribute(name="required")
    private Boolean required;

    @Attribute(name="size")
    private Integer size;

    @ChildList
    private List<Option> options;

    @Label(defaultText = "select")
    private String label;

    public Select(String id, List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled, String form,
                  Boolean multiple, String name, Boolean required, Integer size, List<Option> options, String label) {
        super(id, cssClasses, style, autoFocus, disabled, form, name);
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

    public static SelectBuilder builder() {
        return new SelectBuilder();
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

    public void setLabel(String label) {
        propertyChangeSupport.firePropertyChange("label", this.label, label);
        this.label = label;
    }

    public void addOption(Option option) {

        List<Option> old = new ArrayList<>(options);
        options.add(option);
        propertyChangeSupport.firePropertyChange("options", old, options);

    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Select)) return false;
        final Select other = (Select) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$multiple = this.multiple;
        final Object other$multiple = other.multiple;
        if (this$multiple == null ? other$multiple != null : !this$multiple.equals(other$multiple)) return false;
        final Object this$required = this.required;
        final Object other$required = other.required;
        if (this$required == null ? other$required != null : !this$required.equals(other$required)) return false;
        final Object this$size = this.size;
        final Object other$size = other.size;
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) return false;
        final Object this$options = this.options;
        final Object other$options = other.options;
        if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
        final Object this$label = this.label;
        final Object other$label = other.label;
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Select;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $multiple = this.multiple;
        result = result * PRIME + ($multiple == null ? 43 : $multiple.hashCode());
        final Object $required = this.required;
        result = result * PRIME + ($required == null ? 43 : $required.hashCode());
        final Object $size = this.size;
        result = result * PRIME + ($size == null ? 43 : $size.hashCode());
        final Object $options = this.options;
        result = result * PRIME + ($options == null ? 43 : $options.hashCode());
        final Object $label = this.label;
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        return result;
    }

    public Boolean getMultiple() {
        return this.multiple;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public Integer getSize() {
        return this.size;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public String getLabel() {
        return this.label;
    }

    public static class SelectBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private Boolean autoFocus;
        private Boolean disabled;
        private String form;
        private Boolean multiple;
        private String name;
        private Boolean required;
        private Integer size;
        private ArrayList<Option> options;
        private String label;

        SelectBuilder() {
        }

        public SelectBuilder id(String id) {
            this.id = id;
            return this;
        }

        public SelectBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public SelectBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public SelectBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public SelectBuilder style(String style) {
            this.style = style;
            return this;
        }

        public SelectBuilder autoFocus(Boolean autoFocus) {
            this.autoFocus = autoFocus;
            return this;
        }

        public SelectBuilder disabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public SelectBuilder form(String form) {
            this.form = form;
            return this;
        }

        public SelectBuilder multiple(Boolean multiple) {
            this.multiple = multiple;
            return this;
        }

        public SelectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SelectBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public SelectBuilder size(Integer size) {
            this.size = size;
            return this;
        }

        public SelectBuilder option(Option option) {
            if (this.options == null) this.options = new ArrayList<Option>();
            this.options.add(option);
            return this;
        }

        public SelectBuilder options(Collection<? extends Option> options) {
            if (this.options == null) this.options = new ArrayList<Option>();
            this.options.addAll(options);
            return this;
        }

        public SelectBuilder clearOptions() {
            if (this.options != null)
                this.options.clear();
            return this;
        }

        public SelectBuilder label(String label) {
            this.label = label;
            return this;
        }

        public Select build() {
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
            List<Option> options;
            switch (this.options == null ? 0 : this.options.size()) {
                case 0:
                    options = java.util.Collections.emptyList();
                    break;
                case 1:
                    options = java.util.Collections.singletonList(this.options.get(0));
                    break;
                default:
                    options = java.util.Collections.unmodifiableList(new ArrayList<Option>(this.options));
            }

            return new Select(id, cssClasses, style, autoFocus, disabled, form, multiple, name, required, size, options, label);
        }

        public String toString() {
            return "Select.SelectBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", autoFocus=" + this.autoFocus + ", disabled=" + this.disabled + ", form=" + this.form + ", multiple=" + this.multiple + ", name=" + this.name + ", required=" + this.required + ", size=" + this.size + ", options=" + this.options + ", label=" + this.label + ")";
        }
    }
}
