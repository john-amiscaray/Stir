package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@HTMLElement(tagName = "script")
@RequiredArgsConstructor
@AllArgsConstructor
public class Script {

    @Attribute(name="type")
    private String type;
    @Attribute(name="src")
    private final String src;

}
