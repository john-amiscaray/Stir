package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@HTMLElement(tagName = "a")
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Anchor extends AbstractUIElement{

    @InnerContent
    private final String label;
    @Attribute(name = "href")
    private final String href;
    @Attribute(name = "download")
    private String download;
    @Attribute(name = "hreflang")
    private String hrefLang;
    @Attribute(name = "media")
    private String ping;
    @Attribute(name = "referrerpolicy")
    private String referrerPolicy;
    @Attribute(name = "rel")
    private String rel;
    @Attribute(name = "target")
    private String target;
    @Attribute(name = "type")
    private String type;

}
