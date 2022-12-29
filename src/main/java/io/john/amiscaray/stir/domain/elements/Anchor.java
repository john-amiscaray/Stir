package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing an HTML anchor tag
 */
@HTMLElement(tagName = "a")
public class Anchor extends AbstractUIElement{

    /**
     * The inner content of the anchor tag
     */
    @InnerContent
    private final String label;
    /**
     * The href the anchor tag points to
     */
    @Attribute(name = "href")
    private final String href;
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
     * The ping attribute of the anchor tag
     */
    @Attribute(name = "media")
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

    public Anchor(String id, List<String> cssClasses, String style, String label, String href, String download,
                  String hrefLang, String ping, String referrerPolicy, String rel, String target, String type) {
        super(id, cssClasses, style);
        this.label = label;
        this.href = href;
        this.download = download;
        this.hrefLang = hrefLang;
        this.ping = ping;
        this.referrerPolicy = referrerPolicy;
        this.rel = rel;
        this.target = target;
        this.type = type;
    }

    public Anchor(String label, String href, String download, String hrefLang, String ping, String referrerPolicy, String rel, String target, String type) {
        this.label = label;
        this.href = href;
        this.download = download;
        this.hrefLang = hrefLang;
        this.ping = ping;
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

    public String getPing() {
        return this.ping;
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

    public void setDownload(String download) {
        this.download = download;
    }

    public void setHrefLang(String hrefLang) {
        this.hrefLang = hrefLang;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        this.referrerPolicy = referrerPolicy;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setType(String type) {
        this.type = type;
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
        final Object this$ping = this.getPing();
        final Object other$ping = other.getPing();
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
        final Object $ping = this.getPing();
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
        return "Anchor(label=" + this.getLabel() + ", href=" + this.getHref() + ", download=" + this.getDownload() + ", hrefLang=" + this.getHrefLang() + ", ping=" + this.getPing() + ", referrerPolicy=" + this.getReferrerPolicy() + ", rel=" + this.getRel() + ", target=" + this.getTarget() + ", type=" + this.getType() + ")";
    }

    public static class AnchorBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String label;
        private String href;
        private String download;
        private String hrefLang;
        private String ping;
        private String referrerPolicy;
        private String rel;
        private String target;
        private String type;

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

        public Anchor build() {
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

            return new Anchor(id, cssClasses, style, label, href, download, hrefLang, ping, referrerPolicy, rel, target, type);
        }

        public String toString() {
            return "Anchor.AnchorBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", label=" + this.label + ", href=" + this.href + ", download=" + this.download + ", hrefLang=" + this.hrefLang + ", ping=" + this.ping + ", referrerPolicy=" + this.referrerPolicy + ", rel=" + this.rel + ", target=" + this.target + ", type=" + this.type + ")";
        }
    }
}
