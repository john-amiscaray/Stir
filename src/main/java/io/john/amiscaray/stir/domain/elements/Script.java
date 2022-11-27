package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "script", newLineAfterOpening = false)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Script extends AbstractElement{

    @Attribute(name="src")
    private final String src;
    @Attribute(name="type")
    private String type;
    @Attribute(name="defer")
    private String defer;
    @Attribute(name="integrity")
    private String integrity;
    @Attribute(name="nomodule")
    private Boolean noModule;
    @Attribute(name="referrerpolicy")
    private String referrerPolicy;
    @Attribute(name="async")
    private String async;

}
