package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "h")
@ToString
public class Heading extends AbstractTextElement{

    @Getter
    private Integer level;

    @Builder
    public Heading(String id, @Singular List<String> cssClasses, String style, String content, Integer level) {
        super(id, cssClasses, style, content);
        this.level = level;
    }

    public Heading(Integer level, String content){
        super(null, new ArrayList<>(), null, content);
        this.level = level;
    }

    public void setLevel(Integer level) {
        propertyChangeSupport.firePropertyChange("level", this.level, level);
        this.level = level;
    }


}
