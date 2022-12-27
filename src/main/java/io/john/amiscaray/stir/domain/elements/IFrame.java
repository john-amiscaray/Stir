package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "iframe")
public class IFrame extends AbstractUIElement{

    @Attribute(name = "allow")
    @Getter
    private String allow;

    @Attribute(name = "allowfullscreen")
    @Getter
    private Boolean allowFullScreen;

    @Attribute(name = "height")
    @Getter
    private Integer height;

    @Attribute(name = "loading")
    @Getter
    private String loading;

    @Attribute(name = "name")
    @Getter
    private String name;

    @Attribute(name = "referrerpolicy")
    @Getter
    private String referrerPolicy;

    @Attribute(name = "sandbox")
    @Getter
    private String sandbox;

    @Attribute(name = "src")
    @Getter
    private String src;

    @Attribute(name = "srcdoc")
    @Getter
    private String srcDoc;

    @Attribute(name = "width")
    @Getter
    private Integer width;

    @Builder
    public IFrame(String id, @Singular List<String> cssClasses, String style, String allow, Boolean allowFullScreen,
                  Integer height, String loading, String name, String referrerPolicy,
                  String sandbox, String src, String srcDoc, Integer width) {
        super(id, cssClasses, style);
        this.allow = allow;
        this.allowFullScreen = allowFullScreen;
        this.height = height;
        this.loading = loading;
        this.name = name;
        this.referrerPolicy = referrerPolicy;
        this.sandbox = sandbox;
        this.src = src;
        this.srcDoc = srcDoc;
        this.width = width;
    }

    public IFrame(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public void setAllow(String allow) {
        propertyChangeSupport.firePropertyChange("allow", this.allow, allow);
        this.allow = allow;
    }

    public void setAllowFullScreen(Boolean allowFullScreen) {
        propertyChangeSupport.firePropertyChange("allowFullScreen", this.allowFullScreen, allowFullScreen);
        this.allowFullScreen = allowFullScreen;
    }

    public void setHeight(Integer height) {
        propertyChangeSupport.firePropertyChange("height", this.height, height);
        this.height = height;
    }

    public void setLoading(String loading) {
        propertyChangeSupport.firePropertyChange("loading", this.loading, loading);
        this.loading = loading;
    }

    public void setName(String name) {
        propertyChangeSupport.firePropertyChange("name", this.name, name);
        this.name = name;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        propertyChangeSupport.firePropertyChange("referrerPolicy", this.referrerPolicy, referrerPolicy);
        this.referrerPolicy = referrerPolicy;
    }

    public void setSandbox(String sandbox) {
        propertyChangeSupport.firePropertyChange("sandbox", this.sandbox, sandbox);
        this.sandbox = sandbox;
    }

    public void setSrc(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public void setSrcDoc(String srcDoc) {
        propertyChangeSupport.firePropertyChange("srcDoc", this.srcDoc, srcDoc);
        this.srcDoc = srcDoc;
    }

    public void setWidth(Integer width) {
        propertyChangeSupport.firePropertyChange("width", this.width, width);
        this.width = width;
    }

}
