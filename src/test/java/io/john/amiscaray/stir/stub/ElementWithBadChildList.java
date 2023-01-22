package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.UnorderedList;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "el")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementWithBadChildList extends AbstractUIElement {

    @ChildList
    private UnorderedList list;

}
