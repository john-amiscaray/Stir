package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "p")
@Data
@AllArgsConstructor
public class Paragraph extends AbstractUIElement {

    @InnerContent
    private String content;

}
