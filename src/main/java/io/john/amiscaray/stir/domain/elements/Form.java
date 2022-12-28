package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "form")
@AllArgsConstructor
public class Form extends AbstractUIElement {

    @ChildList
    private List<Input> fields;

    @Attribute(name = "method", defaultValue = "get")
    @Getter
    private String method;

    @Attribute(name="action", defaultValue = "/")
    @Getter
    private String action;

    @Builder
    public Form(String id, @Singular List<String> cssClasses, String style, @Singular List<Input> fields, String method, String action) {
        super(id, cssClasses, style);
        this.fields = fields;
        this.method = method;
        this.action = action;
    }

    public void setMethod(String method) {
        propertyChangeSupport.firePropertyChange("method", this.method, method);
        this.method = method;
    }

    public void setAction(String action) {
        propertyChangeSupport.firePropertyChange("action", this.action, action);
        this.action = action;
    }

}
