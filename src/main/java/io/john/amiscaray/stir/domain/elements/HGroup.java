package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "hgroup")
public class HGroup extends AbstractElementContainer<AbstractTextElement>{

    @Builder
    public HGroup(String id, @Singular List<String> cssClasses, String style, @Singular List<AbstractTextElement> children) {
        super(id, cssClasses, style, children);
    }

}
