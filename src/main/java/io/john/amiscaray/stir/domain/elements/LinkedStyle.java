package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@HTMLElement(tagName="link", hasClosing = false)
public class LinkedStyle extends AbstractUIElement {

    @Attribute(name="href", defaultValue="./styles.css")
    @Getter
    private final String href;
    @Attribute(name="rel")
    @Getter
    private final String rel = "stylesheet";
    @Attribute(name="integrity")
    @Getter
    private String integrity;
    @Attribute(name="crossorigin")
    @Getter
    private String crossOrigin;

    @Builder
    public LinkedStyle(String id, @Singular List<String> cssClasses, String style, String href, String integrity, String crossOrigin) {
        super(id, cssClasses, style);
        this.href = href;
        this.integrity = integrity;
        this.crossOrigin = crossOrigin;
    }

    public void setIntegrity(String integrity) {
        propertyChangeSupport.firePropertyChange("integrity", this.integrity, integrity);
        this.integrity = integrity;
    }

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
    }

}
