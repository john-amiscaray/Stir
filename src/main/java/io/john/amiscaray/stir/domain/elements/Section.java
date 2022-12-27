package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "section")
public class Section extends AbstractElementContainer<AbstractUIElement>{

    @Builder
    public Section(String id, @Singular List<String> cssClasses, String style, @Singular List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }

}
