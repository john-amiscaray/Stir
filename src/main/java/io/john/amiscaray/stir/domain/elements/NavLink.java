package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "li")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavLink extends AbstractUIElement{

    @Nested
    private Anchor a;

    public static NavLink fromLabelAndHref(String label, String href){

        return new NavLink(new Anchor(label, href));

    }

}
