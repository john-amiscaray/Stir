package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@HTMLElement(tagName = "p")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElementWithLongAndFloat extends AbstractUIElement {

    @Attribute(name="my-float")
    private Float myFloat;

    @Attribute(name="my-long")
    private Long myLong;

    @Builder
    public ElementWithLongAndFloat(String id, List<String> cssClasses, String style, Float myFloat, Long myLong) {
        super(id, cssClasses, style);
        this.myFloat = myFloat;
        this.myLong = myLong;
    }

}
