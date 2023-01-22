package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "p")
@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class ElementWithBadClassList extends AbstractUIElement {

    @ClassList
    private String myClasses;

    @InnerContent
    private String content;

}
