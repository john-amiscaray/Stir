package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;

@HTMLElement(tagName = "meta", hasClosing = false)
public class Meta extends AbstractUIElement{

    @Attribute(name="http-equiv")
    private String httpEquiv;

    @Attribute(name="name")
    private final String name;

    @Attribute(name="content")
    private final String content;

    @Attribute(name="charset")
    private String charset;

    public Meta(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Meta(String httpEquiv, String name, String content, String charset) {
        this.httpEquiv = httpEquiv;
        this.name = name;
        this.content = content;
        this.charset = charset;
    }

    public static MetaBuilder builder() {
        return new MetaBuilder();
    }

    public void setHttpEquiv(String httpEquiv) {
        propertyChangeSupport.firePropertyChange("httpEquiv", this.httpEquiv, httpEquiv);
        this.httpEquiv = httpEquiv;
    }

    public void setCharset(String charset) {
        propertyChangeSupport.firePropertyChange("charset", this.charset, charset);
        this.charset = charset;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Meta)) return false;
        final Meta other = (Meta) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$httpEquiv = this.httpEquiv;
        final Object other$httpEquiv = other.httpEquiv;
        if (this$httpEquiv == null ? other$httpEquiv != null : !this$httpEquiv.equals(other$httpEquiv)) return false;
        final Object this$name = this.name;
        final Object other$name = other.name;
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$content = this.content;
        final Object other$content = other.content;
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$charset = this.charset;
        final Object other$charset = other.charset;
        if (this$charset == null ? other$charset != null : !this$charset.equals(other$charset)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Meta;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $httpEquiv = this.httpEquiv;
        result = result * PRIME + ($httpEquiv == null ? 43 : $httpEquiv.hashCode());
        final Object $name = this.name;
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $content = this.content;
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $charset = this.charset;
        result = result * PRIME + ($charset == null ? 43 : $charset.hashCode());
        return result;
    }

    public String getHttpEquiv() {
        return this.httpEquiv;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public String getCharset() {
        return this.charset;
    }

    public static class MetaBuilder {
        private String httpEquiv;
        private String name;
        private String content;
        private String charset;

        MetaBuilder() {
        }

        public MetaBuilder httpEquiv(String httpEquiv) {
            this.httpEquiv = httpEquiv;
            return this;
        }

        public MetaBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MetaBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MetaBuilder charset(String charset) {
            this.charset = charset;
            return this;
        }

        public Meta build() {
            return new Meta(httpEquiv, name, content, charset);
        }

        public String toString() {
            return "Meta.MetaBuilder(httpEquiv=" + this.httpEquiv + ", name=" + this.name + ", content=" + this.content + ", charset=" + this.charset + ")";
        }
    }
}
