package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "script", newLineAfterOpening = false)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Script extends AbstractUIElement {

    @Attribute(name="src")
    @Getter
    private final String src;
    @Attribute(name="type")
    @Getter
    private String type;
    @Attribute(name="defer")
    @Getter
    private String defer;
    @Attribute(name="integrity")
    @Getter
    private String integrity;
    @Attribute(name="nomodule")
    @Getter
    private Boolean noModule;
    @Attribute(name="referrerpolicy")
    @Getter
    private String referrerPolicy;
    @Attribute(name="async")
    @Getter
    private String async;

    public void setType(String type) {
        propertyChangeSupport.firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public void setDefer(String defer) {
        propertyChangeSupport.firePropertyChange("defer", this.defer, defer);
        this.defer = defer;
    }

    public void setIntegrity(String integrity) {
        propertyChangeSupport.firePropertyChange("integrity", this.integrity, integrity);
        this.integrity = integrity;
    }

    public void setNoModule(Boolean noModule) {
        propertyChangeSupport.firePropertyChange("noModule", this.noModule, noModule);
        this.noModule = noModule;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        propertyChangeSupport.firePropertyChange("referrerPolicy", this.referrerPolicy, referrerPolicy);
        this.referrerPolicy = referrerPolicy;
    }

    public void setAsync(String async) {
        propertyChangeSupport.firePropertyChange("async", this.async, async);
        this.async = async;
    }
}
