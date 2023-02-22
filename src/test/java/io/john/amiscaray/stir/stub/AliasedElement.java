package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "ul", alias = "foo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliasedElement extends AbstractUIElement {

    @Attribute(name = "bar")
    private String bar;

    @Attribute(name = "lorem")
    private String lorem;

}
