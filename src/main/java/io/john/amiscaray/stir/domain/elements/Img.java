package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@HTMLElement(tagName = "img", hasClosing = false)
public class Img extends AbstractUIElement{

    @Attribute(name = "alt")
    private String alt;

    @Attribute(name = "crossorigin")
    private String crossOrigin;

    @Attribute(name = "height")
    private Integer height;

    @Attribute(name = "ismap")
    private Boolean isMap;

    @Attribute(name = "loading")
    private String loading;

    @Attribute(name = "longdesc")
    private String longDesc;

    @Attribute(name = "referrerpolicy")
    private String referrerPolicy;

    @Attribute(name = "sizes")
    private String sizes;

    @Attribute(name = "src")
    private String src;

    @Attribute(name = "srcset")
    private String srcSet;

    @Attribute(name = "usemap")
    private String useMap;

    @Attribute(name = "width")
    private Integer width;

    @Builder
    public Img(String id, @Singular List<String> cssClasses, String style, String alt, String crossOrigin, Integer height,
               Boolean isMap, String loading, String longDesc, String referrerPolicy, String sizes, String src, String srcSet, String useMap, Integer width) {
        super(id, cssClasses, style);
        this.alt = alt;
        this.crossOrigin = crossOrigin;
        this.height = height;
        this.isMap = isMap;
        this.loading = loading;
        this.longDesc = longDesc;
        this.referrerPolicy = referrerPolicy;
        this.sizes = sizes;
        this.src = src;
        this.srcSet = srcSet;
        this.useMap = useMap;
        this.width = width;
    }

    public Img(String alt, String src) {
        this.alt = alt;
        this.src = src;
    }

    public void setAlt(String alt) {
        propertyChangeSupport.firePropertyChange("alt", this.alt, alt);
        this.alt = alt;
    }

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
    }

    public void setHeight(Integer height) {
        propertyChangeSupport.firePropertyChange("height", this.height, height);
        this.height = height;
    }

    public void setMap(Boolean map) {
        propertyChangeSupport.firePropertyChange("map", this.isMap, map);
        isMap = map;
    }

    public void setLoading(String loading) {
        propertyChangeSupport.firePropertyChange("loading", this.loading, loading);
        this.loading = loading;
    }

    public void setLongDesc(String longDesc) {
        propertyChangeSupport.firePropertyChange("longDesc", this.longDesc, longDesc);
        this.longDesc = longDesc;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        propertyChangeSupport.firePropertyChange("referrerPolicy", this.referrerPolicy, referrerPolicy);
        this.referrerPolicy = referrerPolicy;
    }

    public void setSizes(String sizes) {
        propertyChangeSupport.firePropertyChange("sizes", this.sizes, sizes);
        this.sizes = sizes;
    }

    public void setSrc(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public void setSrcSet(String srcSet) {
        propertyChangeSupport.firePropertyChange("srcSet", this.srcSet, srcSet);
        this.srcSet = srcSet;
    }

    public void setUseMap(String useMap) {
        propertyChangeSupport.firePropertyChange("useMap", this.useMap, useMap);
        this.useMap = useMap;
    }

    public void setWidth(Integer width) {
        propertyChangeSupport.firePropertyChange("width", this.width, width);
        this.width = width;
    }
}
