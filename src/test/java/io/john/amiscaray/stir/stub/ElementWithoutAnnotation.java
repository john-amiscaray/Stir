package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementWithoutAnnotation extends AbstractUIElement {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "thing")
    private String thing;

}
