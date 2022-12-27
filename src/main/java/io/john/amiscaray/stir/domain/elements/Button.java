package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "button")
public class Button extends AbstractFormField{

    @InnerContent
    @Getter
    private String content;

    @Attribute(name = "formaction")
    @Getter
    private String formAction;

    @Attribute(name = "formenctype")
    @Getter
    private String formEnctype;

    @Attribute(name = "formmethod")
    @Getter
    private String formMethod;

    @Attribute(name = "formnovalidate")
    @Getter
    private Boolean formNoValidate;

    @Attribute(name = "formtarget")
    @Getter
    private String formTarget;

    @Attribute(name = "type")
    @Getter
    private String type;

    @Attribute(name = "value")
    @Getter
    private String value;

    @Builder
    public Button(String id, @Singular List<String> cssClasses, String style, Boolean autoFocus, Boolean disabled,
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

}
