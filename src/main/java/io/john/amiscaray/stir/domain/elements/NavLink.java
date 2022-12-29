package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "li")
@Data
public class NavLink extends AbstractUIElement{

    @Nested
    private Anchor a;

    @Builder
    public NavLink(String id, @Singular List<String> cssClasses, String style, Anchor a) {
        super(id, cssClasses, style);
        this.a = a;
    }

    public NavLink(Anchor a) {
        this.a = a;
    }

    public static NavLink fromLabelAndHref(String label, String href){

        return new NavLink(new Anchor(label, href));

    }

}
