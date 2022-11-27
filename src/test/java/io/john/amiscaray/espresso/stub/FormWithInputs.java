package io.john.amiscaray.espresso.stub;

import io.john.amiscaray.espresso.annotation.HTMLElement;
import io.john.amiscaray.espresso.annotation.Nested;
import io.john.amiscaray.espresso.domain.elements.Input;
import lombok.Data;

@HTMLElement(tagName = "form")
@Data
public class FormWithInputs {

    @Nested
    private Input username = new Input(null, "username", "John", null);

    @Nested
    private Input message = new Input(null, "message", "Some Message", null);

}
