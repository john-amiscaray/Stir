package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a form element
 */
@HTMLElement(tagName = "form")
@NoArgsConstructor
public class Form extends AbstractUIElement {

    /**
     * The input fields within the form
     */
    @ChildList(childrenType = Input.class)
    private List<Input> fields;

    /**
     * The method attribute of the form
     */
    @Attribute(name = "method", defaultValue = "get")
    private String method;

    /**
     * The action attribute of the form
     */
    @Attribute(name="action", defaultValue = "/")
    private String action;

    public Form(String id, List<String> cssClasses, String style, List<Input> fields, String method, String action, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.fields = fields;
        this.method = method;
        this.action = action;
    }

    public Form(List<Input> fields, String method, String action) {
        this.fields = fields;
        this.method = method;
        this.action = action;
    }

    public static FormBuilder builder() {
        return new FormBuilder();
    }

    public void setMethod(String method) {
        propertyChangeSupport.firePropertyChange("method", this.method, method);
        this.method = method;
    }

    public void setAction(String action) {
        propertyChangeSupport.firePropertyChange("action", this.action, action);
        this.action = action;
    }

    public List<Input> getFields() {
        return fields;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Form)) return false;
        final Form other = (Form) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$fields = this.fields;
        final Object other$fields = other.fields;
        if (this$fields == null ? other$fields != null : !this$fields.equals(other$fields)) return false;
        final Object this$method = this.method;
        final Object other$method = other.method;
        if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
        final Object this$action = this.action;
        final Object other$action = other.action;
        if (this$action == null ? other$action != null : !this$action.equals(other$action)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Form;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $fields = this.fields;
        result = result * PRIME + ($fields == null ? 43 : $fields.hashCode());
        final Object $method = this.method;
        result = result * PRIME + ($method == null ? 43 : $method.hashCode());
        final Object $action = this.action;
        result = result * PRIME + ($action == null ? 43 : $action.hashCode());
        return result;
    }

    public String getMethod() {
        return this.method;
    }

    public String getAction() {
        return this.action;
    }

    public static class FormBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<Input> fields;
        private String method;
        private String action;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        FormBuilder() {
        }

        public FormBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FormBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public FormBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public FormBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public FormBuilder style(String style) {
            this.style = style;
            return this;
        }

        public FormBuilder field(Input field) {
            if (this.fields == null) this.fields = new ArrayList<Input>();
            this.fields.add(field);
            return this;
        }

        public FormBuilder fields(Collection<? extends Input> fields) {
            if (this.fields == null) this.fields = new ArrayList<Input>();
            this.fields.addAll(fields);
            return this;
        }

        public FormBuilder clearFields() {
            if (this.fields != null)
                this.fields.clear();
            return this;
        }

        public FormBuilder method(String method) {
            this.method = method;
            return this;
        }

        public FormBuilder action(String action) {
            this.action = action;
            return this;
        }

        public FormBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public FormBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public FormBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public FormBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Form build() {
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
            List<Input> fields;
            switch (this.fields == null ? 0 : this.fields.size()) {
                case 0:
                    fields = java.util.Collections.emptyList();
                    break;
                case 1:
                    fields = java.util.Collections.singletonList(this.fields.get(0));
                    break;
                default:
                    fields = java.util.Collections.unmodifiableList(new ArrayList<Input>(this.fields));
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

            return new Form(id, cssClasses, style, fields, method, action, hidden, customAttributes);
        }

        public String toString() {
            return "Form.FormBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", fields=" + this.fields + ", method=" + this.method + ", action=" + this.action + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
