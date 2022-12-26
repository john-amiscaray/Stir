package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "li")
@Data
public class ListItem extends AbstractUIElement{

    @InnerContent
    private final String content;

    @Builder
    public ListItem(String id, @Singular List<String> cssClasses, String style, String content) {
        super(id, cssClasses, style);
        this.content = content;
    }

    public ListItem(String content) {
        this.content = content;
    }

}
