package io.john.amiscaray.stub;

import io.john.amiscaray.annotation.HTMLElement;
import io.john.amiscaray.annotation.Nested;
import io.john.amiscaray.domain.elements.Input;
import lombok.Data;

@HTMLElement(tagName = "form")
@Data
public class FormWithInputs {

    @Nested
    private Input username = new Input("text", "username", "John");

    @Nested
    private Input message = new Input("text", "message", "Some Message");

}
