package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.AllArgsConstructor;
import lombok.Data;

@HTMLElement(tagName = "p")
@Data
@AllArgsConstructor
public class Paragraph {

    @InnerContent
    private String content;

}
