package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@HTMLElement(tagName = "meta", hasClosing = false)
public class Meta {

    @Attribute(name="http-equiv")
    private String httpEquiv;
    @Attribute(name="name")
    private final String name;
    @Attribute(name="content")
    private final String content;
    @Attribute(name="charset")
    private String charset;

}
