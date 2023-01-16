package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing an iframe element
 */
@HTMLElement(tagName = "iframe")
@NoArgsConstructor
public class IFrame extends AbstractContentFrame{

    /**
     * The allow attribute of the IFrame
     */
    @Attribute(name = "allow")
    private String allow;

    /**
     * The allowfullscreen attribute of the IFrame
     */
    @Attribute(name = "allowfullscreen")
    private Boolean allowFullScreen;

    /**
     * The loading attribute of the IFrame
     */
    @Attribute(name = "loading")
    private String loading;

    /**
     * The name attribute of the IFrame
     */
    @Attribute(name = "name")
    private String name;

    /**
     * The referrerpolicy of the IFrame
     */
    @Attribute(name = "referrerpolicy")
    private String referrerPolicy;

    /**
     * The sandbox attribute of the IFrame
     */
    @Attribute(name = "sandbox")
    private String sandbox;

    /**
     * The srcdoc of the IFrame
     */
    @Attribute(name = "srcdoc")
    private String srcDoc;

    public IFrame(String id, List<String> cssClasses, String style, String allow, Boolean allowFullScreen,
                  Integer height, String loading, String name, String referrerPolicy,
                  String sandbox, String src, String srcDoc, Integer width, boolean hidden) {
        super(id, cssClasses, style, height, src, width, hidden);
        this.allow = allow;
        this.allowFullScreen = allowFullScreen;
        this.loading = loading;
        this.name = name;
        this.referrerPolicy = referrerPolicy;
        this.sandbox = sandbox;
        this.srcDoc = srcDoc;
    }

    public IFrame(String src) {
        propertyChangeSupport.firePropertyChange("src", this.src, src);
        this.src = src;
    }

    public static IFrameBuilder builder() {
        return new IFrameBuilder();
    }

    public void setAllow(String allow) {
        propertyChangeSupport.firePropertyChange("allow", this.allow, allow);
        this.allow = allow;
    }

    public void setAllowFullScreen(Boolean allowFullScreen) {
        propertyChangeSupport.firePropertyChange("allowFullScreen", this.allowFullScreen, allowFullScreen);
        this.allowFullScreen = allowFullScreen;
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

    public void setSrcDoc(String srcDoc) {
        propertyChangeSupport.firePropertyChange("srcDoc", this.srcDoc, srcDoc);
        this.srcDoc = srcDoc;
    }

    public String getAllow() {
        return this.allow;
    }

    public Boolean getAllowFullScreen() {
        return this.allowFullScreen;
    }

    public String getLoading() {
        return this.loading;
    }

    public String getName() {
        return this.name;
    }

    public String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    public String getSandbox() {
        return this.sandbox;
    }

    public String getSrcDoc() {
        return this.srcDoc;
    }

    public static class IFrameBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String allow;
        private Boolean allowFullScreen;
        private Integer height;
        private String loading;
        private String name;
        private String referrerPolicy;
        private String sandbox;
        private String src;
        private String srcDoc;
        private Integer width;
        private boolean hidden;

        IFrameBuilder() {
        }

        public IFrameBuilder id(String id) {
            this.id = id;
            return this;
        }

        public IFrameBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public IFrameBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public IFrameBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public IFrameBuilder style(String style) {
            this.style = style;
            return this;
        }

        public IFrameBuilder allow(String allow) {
            this.allow = allow;
            return this;
        }

        public IFrameBuilder allowFullScreen(Boolean allowFullScreen) {
            this.allowFullScreen = allowFullScreen;
            return this;
        }

        public IFrameBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public IFrameBuilder loading(String loading) {
            this.loading = loading;
            return this;
        }

        public IFrameBuilder name(String name) {
            this.name = name;
            return this;
        }

        public IFrameBuilder referrerPolicy(String referrerPolicy) {
            this.referrerPolicy = referrerPolicy;
            return this;
        }

        public IFrameBuilder sandbox(String sandbox) {
            this.sandbox = sandbox;
            return this;
        }

        public IFrameBuilder src(String src) {
            this.src = src;
            return this;
        }

        public IFrameBuilder srcDoc(String srcDoc) {
            this.srcDoc = srcDoc;
            return this;
        }

        public IFrameBuilder width(Integer width) {
            this.width = width;
            return this;
        }

        public IFrameBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public IFrame build() {
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

            return new IFrame(id, cssClasses, style, allow, allowFullScreen, height, loading, name, referrerPolicy, sandbox, src, srcDoc, width, hidden);
        }

        public String toString() {
            return "IFrame.IFrameBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", allow=" + this.allow + ", allowFullScreen=" + this.allowFullScreen + ", height=" + this.height + ", loading=" + this.loading + ", name=" + this.name + ", referrerPolicy=" + this.referrerPolicy + ", sandbox=" + this.sandbox + ", src=" + this.src + ", srcDoc=" + this.srcDoc + ", width=" + this.width + ", hidden=" + this.hidden + ")";
        }
    }
}
