package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a button element
 */
@HTMLElement(tagName = "button")
@NoArgsConstructor
public class Button extends AbstractFormField{

    /**
     * Inner content of the button
     */
    @InnerContent
    private String content;

    /**
     * The formaction attribute of the button
     */
    @Attribute(name = "formaction")
    private String formAction;

    /**
     * The formenctype attribute of the button
     */
    @Attribute(name = "formenctype")
    private String formEnctype;

    /**
     * The formmethod attribute of the button
     */
    @Attribute(name = "formmethod")
    private String formMethod;

    /**
     * The formnovalidate attribute of the button
     */
    @Attribute(name = "formnovalidate")
    private Boolean formNoValidate;

    /**
     * The formtarget attribute of the button
     */
    @Attribute(name = "formtarget")
    private String formTarget;

    /**
     * The type attribute of the button
     */
    @Attribute(name = "type")
    private String type;

    /**
     * The value attribute of the button
     */
    @Attribute(name = "value")
    private String value;

    public Button(String id, List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled,
                  String form, String name, String content, String formAction, String formEnctype, String formMethod,
                  Boolean formNoValidate, String type, String value) {
        super(id, cssClasses, style, autoFocus, disabled, form, name);
        this.content = content;
        this.formAction = formAction;
        this.formEnctype = formEnctype;
        this.formMethod = formMethod;
        this.type = type;
        this.value = value;
        this.formNoValidate = formNoValidate;
    }

    public static ButtonBuilder builder() {
        return new ButtonBuilder();
    }

    public void setContent(String content) {
        propertyChangeSupport.firePropertyChange("content", this.content, content);
        this.content = content;
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

    public void setType(String type) {
        propertyChangeSupport.firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public void setValue(String value) {
        propertyChangeSupport.firePropertyChange("value", this.value, value);
        this.value = value;
    }

    public String getContent() {
        return this.content;
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

    public String getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public static class ButtonBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private Boolean autoFocus;
        private Boolean disabled;
        private String form;
        private String name;
        private String content;
        private String formAction;
        private String formEnctype;
        private String formMethod;
        private Boolean formNoValidate;
        private String type;
        private String value;

        ButtonBuilder() {
        }

        public ButtonBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ButtonBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public ButtonBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public ButtonBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public ButtonBuilder style(String style) {
            this.style = style;
            return this;
        }

        public ButtonBuilder autoFocus(Boolean autoFocus) {
            this.autoFocus = autoFocus;
            return this;
        }

        public ButtonBuilder disabled(Boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public ButtonBuilder form(String form) {
            this.form = form;
            return this;
        }

        public ButtonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ButtonBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ButtonBuilder formAction(String formAction) {
            this.formAction = formAction;
            return this;
        }

        public ButtonBuilder formEnctype(String formEnctype) {
            this.formEnctype = formEnctype;
            return this;
        }

        public ButtonBuilder formMethod(String formMethod) {
            this.formMethod = formMethod;
            return this;
        }

        public ButtonBuilder formNoValidate(Boolean formNoValidate) {
            this.formNoValidate = formNoValidate;
            return this;
        }

        public ButtonBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ButtonBuilder value(String value) {
            this.value = value;
            return this;
        }

        public Button build() {
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

            return new Button(id, cssClasses, style, autoFocus, disabled, form, name, content, formAction, formEnctype, formMethod, formNoValidate, type, value);
        }

        public String toString() {
            return "Button.ButtonBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", autoFocus=" + this.autoFocus + ", disabled=" + this.disabled + ", form=" + this.form + ", name=" + this.name + ", content=" + this.content + ", formAction=" + this.formAction + ", formEnctype=" + this.formEnctype + ", formMethod=" + this.formMethod + ", formNoValidate=" + this.formNoValidate + ", type=" + this.type + ", value=" + this.value + ")";
        }
    }
}
