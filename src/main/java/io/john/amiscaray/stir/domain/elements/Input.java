package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "input", hasClosing = false)
@AllArgsConstructor
@Builder
public class Input extends AbstractUIElement {

    @Attribute(name="type", defaultValue="text")
    @Getter
    private String type;

    @Attribute(name="value", defaultValue="")
    @Getter
    private String value;

    @Label(defaultText="")
    @Getter
    private String label;

    public Input(String id, String type, String value, String label){

        this.id = id;
        this.type = type;
        this.value = value;
        this.label = label;

    }

    public void setType(String type) {
        propertyChangeSupport.firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public void setValue(String value) {
        propertyChangeSupport.firePropertyChange("value", this.value, value);
        this.value = value;
    }

    public void setLabel(String label) {
        propertyChangeSupport.firePropertyChange("label", this.label, label);
        this.label = label;
    }
}
