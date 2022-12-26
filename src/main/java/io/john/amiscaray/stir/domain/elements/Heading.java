package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "h")
@ToString
@AllArgsConstructor
@Builder
public class Heading extends AbstractUIElement{

    @Getter
    private Integer level;
    @InnerContent
    @Getter
    private String innerContent;

    public void setLevel(Integer level) {
        propertyChangeSupport.firePropertyChange("level", this.level, level);
        this.level = level;
    }

    public void setInnerContent(String innerContent) {
        propertyChangeSupport.firePropertyChange("innerContent", this.innerContent, innerContent);
        this.innerContent = innerContent;
    }

}
