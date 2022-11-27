package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@HTMLElement(tagName="link", hasClosing = false)
@Builder
public class Style extends AbstractElement{

    @Attribute(name="href", defaultValue="./styles.css")
    private final String href;
    @Attribute(name="rel")
    private final String rel = "stylesheet";
    @Attribute(name="integrity")
    private String integrity;
    @Attribute(name="crossorigin")
    private String crossOrigin;

}
