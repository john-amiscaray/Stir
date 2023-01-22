package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "something")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementWithBadInnerContent extends AbstractUIElement {

    @InnerContent
    private Integer content;

}
