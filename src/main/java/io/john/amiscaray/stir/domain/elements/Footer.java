package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;

import java.util.List;

@HTMLElement(tagName = "footer")
public class Footer extends Div{

    @Builder(builderMethodName = "footerBuilder")
    public Footer(String id, List<String> cssClasses, String style, List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }

    public static Footer fromDiv(Div div){

        return new Footer(div.getId(), div.getCssClasses(), div.getStyle(), div.getChildren());

    }

}
