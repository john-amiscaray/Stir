package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "p")
public class Paragraph extends AbstractTextElement {

    @Builder
    public Paragraph(String id, @Singular List<String> cssClasses, String style, String content) {
        super(id, cssClasses, style, content);
    }

    public Paragraph(String content){

        super(null, new ArrayList<>(), null, content);

    }

}
