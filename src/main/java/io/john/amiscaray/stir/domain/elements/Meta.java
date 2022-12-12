package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@HTMLElement(tagName = "meta", hasClosing = false)
public class Meta extends CacheableElement{

    @Attribute(name="http-equiv")
    @Getter
    private String httpEquiv;
    @Attribute(name="name")
    @Getter
    private final String name;
    @Attribute(name="content")
    @Getter
    private final String content;
    @Attribute(name="charset")
    @Getter
    private String charset;

    public void setHttpEquiv(String httpEquiv) {
        propertyChangeSupport.firePropertyChange("httpEquiv", this.httpEquiv, httpEquiv);
        this.httpEquiv = httpEquiv;
    }

    public void setCharset(String charset) {
        propertyChangeSupport.firePropertyChange("charset", this.charset, charset);
        this.charset = charset;
    }
}
