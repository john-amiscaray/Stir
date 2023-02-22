package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing an img element
 */
@HTMLElement(tagName = "img", hasClosing = false)
@NoArgsConstructor
public class Img extends AbstractContentFrame{

    /**
     * The alt attribute of the img
     */
    @Attribute(name = "alt")
    private String alt;

    /**
     * The crossorigin attribute of the img
     */
    @Attribute(name = "crossorigin")
    private String crossOrigin;

    /**
     * The ismap attribute of the img
     */
    @Attribute(name = "ismap")
    private Boolean isMap;

    /**
     * The loading attribute of the img
     */
    @Attribute(name = "loading")
    private String loading;

    /**
     * The longdesc attribute of the img
     */
    @Attribute(name = "longdesc")
    private String longDesc;

    /**
     * The referrerpolicy attribute of the img
     */
    @Attribute(name = "referrerpolicy")
    private String referrerPolicy;

    /**
     * The sizes attribute of the img
     */
    @Attribute(name = "sizes")
    private String sizes;

    /**
     * The srcset attribute of the img
     */
    @Attribute(name = "srcset")
    private String srcSet;

    /**
     * The usemap attribute of the img
     */
    @Attribute(name = "usemap")
    private String useMap;

    public Img(String id, List<String> cssClasses, String style, String alt, String crossOrigin, Integer height, Boolean isMap, String loading, String longDesc, String referrerPolicy, String sizes, String src, String srcSet, String useMap, Integer width, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, height, src, width, hidden, customAttributes);
        this.alt = alt;
        this.crossOrigin = crossOrigin;
        this.isMap = isMap;
        this.loading = loading;
        this.longDesc = longDesc;
        this.referrerPolicy = referrerPolicy;
        this.sizes = sizes;
        this.srcSet = srcSet;
        this.useMap = useMap;
    }

    public Img(String alt, String src) {
        this.alt = alt;
        this.src = src;
    }

    public static ImgBuilder builder() {
        return new ImgBuilder();
    }

    public void setAlt(String alt) {
        propertyChangeSupport.firePropertyChange("alt", this.alt, alt);
        this.alt = alt;
    }

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
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

    public void setSrcSet(String srcSet) {
        propertyChangeSupport.firePropertyChange("srcSet", this.srcSet, srcSet);
        this.srcSet = srcSet;
    }

    public void setUseMap(String useMap) {
        propertyChangeSupport.firePropertyChange("useMap", this.useMap, useMap);
        this.useMap = useMap;
    }

    public static class ImgBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String alt;
        private String crossOrigin;
        private Integer height;
        private Boolean isMap;
        private String loading;
        private String longDesc;
        private String referrerPolicy;
        private String sizes;
        private String src;
        private String srcSet;
        private String useMap;
        private Integer width;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        ImgBuilder() {
        }

        public ImgBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ImgBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public ImgBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public ImgBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public ImgBuilder style(String style) {
            this.style = style;
            return this;
        }

        public ImgBuilder alt(String alt) {
            this.alt = alt;
            return this;
        }

        public ImgBuilder crossOrigin(String crossOrigin) {
            this.crossOrigin = crossOrigin;
            return this;
        }

        public ImgBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public ImgBuilder isMap(Boolean isMap) {
            this.isMap = isMap;
            return this;
        }

        public ImgBuilder loading(String loading) {
            this.loading = loading;
            return this;
        }

        public ImgBuilder longDesc(String longDesc) {
            this.longDesc = longDesc;
            return this;
        }

        public ImgBuilder referrerPolicy(String referrerPolicy) {
            this.referrerPolicy = referrerPolicy;
            return this;
        }

        public ImgBuilder sizes(String sizes) {
            this.sizes = sizes;
            return this;
        }

        public ImgBuilder src(String src) {
            this.src = src;
            return this;
        }

        public ImgBuilder srcSet(String srcSet) {
            this.srcSet = srcSet;
            return this;
        }

        public ImgBuilder useMap(String useMap) {
            this.useMap = useMap;
            return this;
        }

        public ImgBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public ImgBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public ImgBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public ImgBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            for (final Map.Entry<? extends String, ? extends String> $lombokEntry : customAttributes.entrySet()) {
                this.customAttributes$key.add($lombokEntry.getKey());
                this.customAttributes$value.add($lombokEntry.getValue());
            }
            return this;
        }

        public ImgBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Img build() {
            List<String> cssClasses;
            switch (this.cssClasses == null ? 0 : this.cssClasses.size()) {
                case 0:
                    cssClasses = java.util.Collections.emptyList();
                    break;
                case 1:
                    cssClasses = java.util.Collections.singletonList(this.cssClasses.get(0));
                    break;
                default:
                    cssClasses = java.util.Collections.unmodifiableList(new ArrayList<String>(this.cssClasses));
            }
            Map<String, String> customAttributes;
            switch (this.customAttributes$key == null ? 0 : this.customAttributes$key.size()) {
                case 0:
                    customAttributes = java.util.Collections.emptyMap();
                    break;
                case 1:
                    customAttributes = java.util.Collections.singletonMap(this.customAttributes$key.get(0), this.customAttributes$value.get(0));
                    break;
                default:
                    customAttributes = new java.util.LinkedHashMap<String, String>(this.customAttributes$key.size() < 1073741824 ? 1 + this.customAttributes$key.size() + (this.customAttributes$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.customAttributes$key.size(); $i++)
                        customAttributes.put(this.customAttributes$key.get($i), (String) this.customAttributes$value.get($i));
                    customAttributes = java.util.Collections.unmodifiableMap(customAttributes);
            }

            return new Img(id, cssClasses, style, alt, crossOrigin, height, isMap, loading, longDesc, referrerPolicy, sizes, src, srcSet, useMap, width, hidden, customAttributes);
        }

        public String toString() {
            return "Img.ImgBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", alt=" + this.alt + ", crossOrigin=" + this.crossOrigin + ", height=" + this.height + ", isMap=" + this.isMap + ", loading=" + this.loading + ", longDesc=" + this.longDesc + ", referrerPolicy=" + this.referrerPolicy + ", sizes=" + this.sizes + ", src=" + this.src + ", srcSet=" + this.srcSet + ", useMap=" + this.useMap + ", width=" + this.width + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
