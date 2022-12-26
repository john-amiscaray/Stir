package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.List;

@HTMLElement(tagName = "div")
public class Div extends AbstractElementContainer<AbstractUIElement>{

    @Builder
    public Div(String id, @Singular List<String> cssClasses, String style, @Singular List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }

}
