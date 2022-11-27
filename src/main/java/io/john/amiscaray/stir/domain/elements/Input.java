package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Label;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "input", hasClosing = false)
@AllArgsConstructor
@Data
public class Input extends AbstractElement{

    @Attribute(name="type", defaultValue="text")
    private String type;

    @Attribute(name="value", defaultValue="")
    private String value;

    @Label(defaultText="")
    private String label;

    public Input(String id, String type, String value, String label){

        this.id = id;
        this.type = type;
        this.value = value;
        this.label = label;

    }

}
