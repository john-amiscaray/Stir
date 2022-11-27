package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Id;
import io.john.amiscaray.stir.annotation.Label;
import lombok.AllArgsConstructor;
import lombok.Data;

@HTMLElement(tagName = "input", hasClosing = false)
@AllArgsConstructor
@Data
public class Input {

    @Id
    private String id;

    @Attribute(name="type", defaultValue="text")
    private String type;

    @Attribute(name="value", defaultValue="")
    private String value;

    @Label(defaultText="")
    private String label;

}
