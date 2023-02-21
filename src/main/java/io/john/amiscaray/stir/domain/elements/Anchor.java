package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * A pojo representing an HTML anchor tag
 */
@HTMLElement(tagName = "a")
@NoArgsConstructor
public class Anchor extends AbstractUIElement{

    /**
     * The inner content of the anchor tag
     */
    @InnerContent
    private String label;
    /**
     * The href the anchor tag points to
     */
    @Attribute(name = "href")
    private String href;
    /**
     * The download attribute of the anchor tag
     */
    @Attribute(name = "download")
    private String download;
    /**
     * The hrefLang attribute of the anchor tag
     */
    @Attribute(name = "hreflang")
    private String hrefLang;
    /**
     * The media attribute of the anchor tag
     */
    @Attribute(name = "media")
    private String media;
    /**
     * The ping attribute of the anchor tag
     */
    @Attribute(name = "ping")
    private String ping;
    /**
     * The referrerPolicy attribute of the anchor tag
     */
    @Attribute(name = "referrerpolicy")
    private String referrerPolicy;
    /**
     * The rel attribute of the anchor tag
     */
    @Attribute(name = "rel")
    private String rel;
    /**
     * The target attribute of the anchor tag
     */
    @Attribute(name = "target")
    private String target;
    /**
     * The type attribute of the anchor tag
     */
    @Attribute(name = "type")
    private String type;

    public Anchor(String id, List<String> cssClasses, String style, String label, String href, String download, String hrefLang,
                  String media, String ping, String referrerPolicy, String rel, String target, String type, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.label = label;
        this.href = href;
        this.download = download;
        this.hrefLang = hrefLang;
        this.media = media;
        this.ping = ping;
        this.referrerPolicy = referrerPolicy;
        this.rel = rel;
        this.target = target;
        this.type = type;
    }

    /**
     * @deprecated This constructor contains errors regarding the ping and media attributes. Instantiate this class with a builder, the other constructors, or an element descriptor.
     * @param label
     * @param href
     * @param download
     * @param hrefLang
     * @param ping
     * @param referrerPolicy
     * @param rel
     * @param target
     * @param type
     */
    @Deprecated
    public Anchor(String label, String href, String download, String hrefLang, String ping, String referrerPolicy, String rel, String target, String type) {
        this.label = label;
        this.href = href;
        this.download = download;
        this.hrefLang = hrefLang;
        this.media = ping;
        this.referrerPolicy = referrerPolicy;
        this.rel = rel;
        this.target = target;
        this.type = type;
    }

    public Anchor(String label, String href) {
        this.label = label;
        this.href = href;
    }

    public static AnchorBuilder builder() {
        return new AnchorBuilder();
    }

    public String getLabel() {
        return this.label;
    }

    public String getHref() {
        return this.href;
    }

    public String getDownload() {
        return this.download;
    }

    public String getHrefLang() {
        return this.hrefLang;
    }

    public String getMedia() {
        return this.media;
    }

    public String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    public String getRel() {
        return this.rel;
    }

    public String getTarget() {
        return this.target;
    }

    public String getType() {
        return this.type;
    }

    public String getPing() {
        return ping;
    }

    public void setDownload(String download) {
        propertyChangeSupport.firePropertyChange("download", this.download, download);
        this.download = download;
    }

    public void setHrefLang(String hrefLang) {
        propertyChangeSupport.firePropertyChange("hrefLang", this.hrefLang, hrefLang);
        this.hrefLang = hrefLang;
    }

    public void setMedia(String media) {
        propertyChangeSupport.firePropertyChange("media", this.media, media);
        this.media = media;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        propertyChangeSupport.firePropertyChange("referrerPolicy", this.referrerPolicy, referrerPolicy);
        this.referrerPolicy = referrerPolicy;
    }

    public void setRel(String rel) {
        propertyChangeSupport.firePropertyChange("rel", this.rel, rel);
        this.rel = rel;
    }

    public void setTarget(String target) {
        propertyChangeSupport.firePropertyChange("target", this.target, target);
        this.target = target;
    }

    public void setType(String type) {
        propertyChangeSupport.firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public void setLabel(String label) {
        propertyChangeSupport.firePropertyChange("label", this.label, label);
        this.label = label;
    }

    public void setHref(String href) {
        propertyChangeSupport.firePropertyChange("href", this.href, href);
        this.href = href;
    }

    public void setPing(String ping) {
        propertyChangeSupport.firePropertyChange("ping", this.ping, ping);
        this.ping = ping;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Anchor)) return false;
        final Anchor other = (Anchor) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$href = this.getHref();
        final Object other$href = other.getHref();
        if (this$href == null ? other$href != null : !this$href.equals(other$href)) return false;
        final Object this$download = this.getDownload();
        final Object other$download = other.getDownload();
        if (this$download == null ? other$download != null : !this$download.equals(other$download)) return false;
        final Object this$hrefLang = this.getHrefLang();
        final Object other$hrefLang = other.getHrefLang();
        if (this$hrefLang == null ? other$hrefLang != null : !this$hrefLang.equals(other$hrefLang)) return false;
        final Object this$ping = this.getMedia();
        final Object other$ping = other.getMedia();
        if (this$ping == null ? other$ping != null : !this$ping.equals(other$ping)) return false;
        final Object this$referrerPolicy = this.getReferrerPolicy();
        final Object other$referrerPolicy = other.getReferrerPolicy();
        if (this$referrerPolicy == null ? other$referrerPolicy != null : !this$referrerPolicy.equals(other$referrerPolicy))
            return false;
        final Object this$rel = this.getRel();
        final Object other$rel = other.getRel();
        if (this$rel == null ? other$rel != null : !this$rel.equals(other$rel)) return false;
        final Object this$target = this.getTarget();
        final Object other$target = other.getTarget();
        if (this$target == null ? other$target != null : !this$target.equals(other$target)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Anchor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $href = this.getHref();
        result = result * PRIME + ($href == null ? 43 : $href.hashCode());
        final Object $download = this.getDownload();
        result = result * PRIME + ($download == null ? 43 : $download.hashCode());
        final Object $hrefLang = this.getHrefLang();
        result = result * PRIME + ($hrefLang == null ? 43 : $hrefLang.hashCode());
        final Object $ping = this.getMedia();
        result = result * PRIME + ($ping == null ? 43 : $ping.hashCode());
        final Object $referrerPolicy = this.getReferrerPolicy();
        result = result * PRIME + ($referrerPolicy == null ? 43 : $referrerPolicy.hashCode());
        final Object $rel = this.getRel();
        result = result * PRIME + ($rel == null ? 43 : $rel.hashCode());
        final Object $target = this.getTarget();
        result = result * PRIME + ($target == null ? 43 : $target.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    public String toString() {
        return "Anchor(label=" + this.getLabel() + ", href=" + this.getHref() + ", download=" + this.getDownload() + ", hrefLang=" + this.getHrefLang() + ", ping=" + this.getMedia() + ", referrerPolicy=" + this.getReferrerPolicy() + ", rel=" + this.getRel() + ", target=" + this.getTarget() + ", type=" + this.getType() + ")";
    }

    public static class AnchorBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String label;
        private String href;
        private String download;
        private String hrefLang;
        private String media;
        private String ping;
        private String referrerPolicy;
        private String rel;
        private String target;
        private String type;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        AnchorBuilder() {
        }

        public AnchorBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AnchorBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public AnchorBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public AnchorBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public AnchorBuilder style(String style) {
            this.style = style;
            return this;
        }

        public AnchorBuilder label(String label) {
            this.label = label;
            return this;
        }

        public AnchorBuilder href(String href) {
            this.href = href;
            return this;
        }

        public AnchorBuilder download(String download) {
            this.download = download;
            return this;
        }

        public AnchorBuilder hrefLang(String hrefLang) {
            this.hrefLang = hrefLang;
            return this;
        }

        public AnchorBuilder media(String media) {
            this.media = media;
            return this;
        }

        public AnchorBuilder ping(String ping) {
            this.ping = ping;
            return this;
        }

        public AnchorBuilder referrerPolicy(String referrerPolicy) {
            this.referrerPolicy = referrerPolicy;
            return this;
        }

        public AnchorBuilder rel(String rel) {
            this.rel = rel;
            return this;
        }

        public AnchorBuilder target(String target) {
            this.target = target;
            return this;
        }

        public AnchorBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AnchorBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public AnchorBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public AnchorBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public AnchorBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Anchor build() {
            List<String> cssClasses;
            switch (this.cssClasses == null ? 0 : this.cssClasses.size()) {
                case 0:
                    cssClasses = Collections.emptyList();
                    break;
                case 1:
                    cssClasses = Collections.singletonList(this.cssClasses.get(0));
                    break;
                default:
                    cssClasses = Collections.unmodifiableList(new ArrayList<String>(this.cssClasses));
            }
            Map<String, String> customAttributes;
            switch (this.customAttributes$key == null ? 0 : this.customAttributes$key.size()) {
                case 0:
                    customAttributes = Collections.emptyMap();
                    break;
                case 1:
                    customAttributes = Collections.singletonMap(this.customAttributes$key.get(0), this.customAttributes$value.get(0));
                    break;
                default:
                    customAttributes = new LinkedHashMap<String, String>(this.customAttributes$key.size() < 1073741824 ? 1 + this.customAttributes$key.size() + (this.customAttributes$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.customAttributes$key.size(); $i++)
                        customAttributes.put(this.customAttributes$key.get($i), (String) this.customAttributes$value.get($i));
                    customAttributes = Collections.unmodifiableMap(customAttributes);
            }

            return new Anchor(id, cssClasses, style, label, href, download, hrefLang, media, ping, referrerPolicy, rel, target, type, hidden, customAttributes);
        }

        public String toString() {
            return "Anchor.AnchorBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", label=" + this.label + ", href=" + this.href + ", download=" + this.download + ", hrefLang=" + this.hrefLang + ", media=" + this.media + ", ping=" + this.ping + ", referrerPolicy=" + this.referrerPolicy + ", rel=" + this.rel + ", target=" + this.target + ", type=" + this.type + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
