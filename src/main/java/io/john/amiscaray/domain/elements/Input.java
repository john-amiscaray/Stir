package io.john.amiscaray.domain.elements;

import io.john.amiscaray.annotation.Attribute;
import io.john.amiscaray.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@HTMLElement(tagName = "input", hasClosing = false)
@AllArgsConstructor
@Data
public class Input {

    @Attribute(name="type", defaultValue="text")
    private String type;

    @Attribute(name="id", defaultValue="")
    private String id;

    @Attribute(name="value", defaultValue="")
    private String value;

}
