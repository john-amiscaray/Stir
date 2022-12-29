package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@HTMLElement(tagName="link", hasClosing = false)
public class LinkedStyle extends AbstractUIElement {

    @Attribute(name="href", defaultValue="./styles.css")
    private final String href;
    @Attribute(name="rel")
    private final String rel = "stylesheet";
    @Attribute(name="integrity")
    private String integrity;
    @Attribute(name="crossorigin")
    private String crossOrigin;

    public LinkedStyle(String id, List<String> cssClasses, String style, String href, String integrity, String crossOrigin) {
        super(id, cssClasses, style);
        this.href = href;
        this.integrity = integrity;
        this.crossOrigin = crossOrigin;
    }

    public LinkedStyle(String href) {
        this.href = href;
    }

    public LinkedStyle(String href, String integrity, String crossOrigin) {
        this.href = href;
        this.integrity = integrity;
        this.crossOrigin = crossOrigin;
    }

    public static LinkedStyleBuilder builder() {
        return new LinkedStyleBuilder();
    }

    public void setIntegrity(String integrity) {
        propertyChangeSupport.firePropertyChange("integrity", this.integrity, integrity);
        this.integrity = integrity;
    }

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LinkedStyle)) return false;
        final LinkedStyle other = (LinkedStyle) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$href = this.href;
        final Object other$href = other.href;
        if (this$href == null ? other$href != null : !this$href.equals(other$href)) return false;
        final Object this$rel = this.rel;
        final Object other$rel = other.rel;
        if (this$rel == null ? other$rel != null : !this$rel.equals(other$rel)) return false;
        final Object this$integrity = this.integrity;
        final Object other$integrity = other.integrity;
        if (this$integrity == null ? other$integrity != null : !this$integrity.equals(other$integrity)) return false;
        final Object this$crossOrigin = this.crossOrigin;
        final Object other$crossOrigin = other.crossOrigin;
        if (this$crossOrigin == null ? other$crossOrigin != null : !this$crossOrigin.equals(other$crossOrigin))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LinkedStyle;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $href = this.href;
        result = result * PRIME + ($href == null ? 43 : $href.hashCode());
        final Object $rel = this.rel;
        result = result * PRIME + ($rel == null ? 43 : $rel.hashCode());
        final Object $integrity = this.integrity;
        result = result * PRIME + ($integrity == null ? 43 : $integrity.hashCode());
        final Object $crossOrigin = this.crossOrigin;
        result = result * PRIME + ($crossOrigin == null ? 43 : $crossOrigin.hashCode());
        return result;
    }

    public String getHref() {
        return this.href;
    }

    public String getRel() {
        return this.rel;
    }

    public String getIntegrity() {
        return this.integrity;
    }

    public String getCrossOrigin() {
        return this.crossOrigin;
    }

    public static class LinkedStyleBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String href;
        private String integrity;
        private String crossOrigin;

        LinkedStyleBuilder() {
        }

        public LinkedStyleBuilder id(String id) {
            this.id = id;
            return this;
        }

        public LinkedStyleBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public LinkedStyleBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public LinkedStyleBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public LinkedStyleBuilder style(String style) {
            this.style = style;
            return this;
        }

        public LinkedStyleBuilder href(String href) {
            this.href = href;
            return this;
        }

        public LinkedStyleBuilder integrity(String integrity) {
            this.integrity = integrity;
            return this;
        }

        public LinkedStyleBuilder crossOrigin(String crossOrigin) {
            this.crossOrigin = crossOrigin;
            return this;
        }

        public LinkedStyle build() {
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

            return new LinkedStyle(id, cssClasses, style, href, integrity, crossOrigin);
        }

        public String toString() {
            return "LinkedStyle.LinkedStyleBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", href=" + this.href + ", integrity=" + this.integrity + ", crossOrigin=" + this.crossOrigin + ")";
        }
    }
}
