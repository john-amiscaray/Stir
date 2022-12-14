package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.CacheableElement;
import io.john.amiscaray.stir.domain.elements.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "form")
@Data
public class FormWithInputs extends AbstractUIElement {

    @Nested
    private Input username = new Input(null, "username", "John", null, "username");

    @Nested
    private Input message = new Input(null, "message", "Some Message", null, "message");

}
