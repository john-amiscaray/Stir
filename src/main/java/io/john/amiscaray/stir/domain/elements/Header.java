package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "header")
public class Header extends AbstractElementContainer<AbstractUIElement>{

    public Header(String id, @Singular List<String> cssClasses, String style, @Singular List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }
}
