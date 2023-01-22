package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.TableEntries;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "header")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElementWithBadObjectTable extends AbstractUIElement {

    @TableEntries
    private String table;

}
