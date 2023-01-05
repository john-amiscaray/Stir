package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

/**
 * A pojo representing a script element
 */
@HTMLElement(tagName = "script", newLineAfterOpening = false)
@NoArgsConstructor
public class Script extends AbstractUIElement {

    /**
     * The src attribute of the script
     */
    @Attribute(name="src")
    private String src;

    /**
     * The type attribute of the script
     */
    @Attribute(name="type")
    private String type;

    /**
     * The defer attribute of the script
     */
    @Attribute(name="defer")
    private String defer;

    /**
     * The integrity attribute of the script
     */
    @Attribute(name="integrity")
    private String integrity;

    /**
     * The nomodule attribute of the script
     */
    @Attribute(name="nomodule")
    private Boolean noModule;

    /**
     * The referrerpolicy attribute of the script
     */
    @Attribute(name="referrerpolicy")
    private String referrerPolicy;

    /**
     * The async attribute of the script
     */
    @Attribute(name="async")
    private String async;

    /**
     * The crossorigin attribute of the script
     */
    @Attribute(name="crossorigin")
    private String crossOrigin;

    public Script(String src) {
        this.src = src;
    }

    public Script(String src, String type, String defer, String integrity, Boolean noModule, String referrerPolicy, String async, String crossOrigin) {
        this.src = src;
        this.type = type;
        this.defer = defer;
        this.integrity = integrity;
        this.noModule = noModule;
        this.referrerPolicy = referrerPolicy;
        this.async = async;
        this.crossOrigin = crossOrigin;
    }

    public static ScriptBuilder builder() {
        return new ScriptBuilder();
    }

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

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Script)) return false;
        final Script other = (Script) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$src = this.src;
        final Object other$src = other.src;
        if (this$src == null ? other$src != null : !this$src.equals(other$src)) return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$defer = this.defer;
        final Object other$defer = other.defer;
        if (this$defer == null ? other$defer != null : !this$defer.equals(other$defer)) return false;
        final Object this$integrity = this.integrity;
        final Object other$integrity = other.integrity;
        if (this$integrity == null ? other$integrity != null : !this$integrity.equals(other$integrity)) return false;
        final Object this$noModule = this.noModule;
        final Object other$noModule = other.noModule;
        if (this$noModule == null ? other$noModule != null : !this$noModule.equals(other$noModule)) return false;
        final Object this$referrerPolicy = this.referrerPolicy;
        final Object other$referrerPolicy = other.referrerPolicy;
        if (this$referrerPolicy == null ? other$referrerPolicy != null : !this$referrerPolicy.equals(other$referrerPolicy))
            return false;
        final Object this$async = this.async;
        final Object other$async = other.async;
        if (this$async == null ? other$async != null : !this$async.equals(other$async)) return false;
        final Object this$crossOrigin = this.crossOrigin;
        final Object other$crossOrigin = other.crossOrigin;
        if (this$crossOrigin == null ? other$crossOrigin != null : !this$crossOrigin.equals(other$crossOrigin))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Script;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $src = this.src;
        result = result * PRIME + ($src == null ? 43 : $src.hashCode());
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $defer = this.defer;
        result = result * PRIME + ($defer == null ? 43 : $defer.hashCode());
        final Object $integrity = this.integrity;
        result = result * PRIME + ($integrity == null ? 43 : $integrity.hashCode());
        final Object $noModule = this.noModule;
        result = result * PRIME + ($noModule == null ? 43 : $noModule.hashCode());
        final Object $referrerPolicy = this.referrerPolicy;
        result = result * PRIME + ($referrerPolicy == null ? 43 : $referrerPolicy.hashCode());
        final Object $async = this.async;
        result = result * PRIME + ($async == null ? 43 : $async.hashCode());
        final Object $crossOrigin = this.crossOrigin;
        result = result * PRIME + ($crossOrigin == null ? 43 : $crossOrigin.hashCode());
        return result;
    }

    public String getSrc() {
        return this.src;
    }

    public String getType() {
        return this.type;
    }

    public String getDefer() {
        return this.defer;
    }

    public String getIntegrity() {
        return this.integrity;
    }

    public Boolean getNoModule() {
        return this.noModule;
    }

    public String getReferrerPolicy() {
        return this.referrerPolicy;
    }

    public String getAsync() {
        return this.async;
    }

    public String getCrossOrigin() {
        return this.crossOrigin;
    }

    public static class ScriptBuilder {
        private String src;
        private String type;
        private String defer;
        private String integrity;
        private Boolean noModule;
        private String referrerPolicy;
        private String async;
        private String crossOrigin;

        ScriptBuilder() {
        }

        public ScriptBuilder src(String src) {
            this.src = src;
            return this;
        }

        public ScriptBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ScriptBuilder defer(String defer) {
            this.defer = defer;
            return this;
        }

        public ScriptBuilder integrity(String integrity) {
            this.integrity = integrity;
            return this;
        }

        public ScriptBuilder noModule(Boolean noModule) {
            this.noModule = noModule;
            return this;
        }

        public ScriptBuilder referrerPolicy(String referrerPolicy) {
            this.referrerPolicy = referrerPolicy;
            return this;
        }

        public ScriptBuilder async(String async) {
            this.async = async;
            return this;
        }

        public ScriptBuilder crossOrigin(String crossOrigin) {
            this.crossOrigin = crossOrigin;
            return this;
        }

        public Script build() {
            return new Script(src, type, defer, integrity, noModule, referrerPolicy, async, crossOrigin);
        }

        public String toString() {
            return "Script.ScriptBuilder(src=" + this.src + ", type=" + this.type + ", defer=" + this.defer + ", integrity=" + this.integrity + ", noModule=" + this.noModule + ", referrerPolicy=" + this.referrerPolicy + ", async=" + this.async + ", crossOrigin=" + this.crossOrigin + ")";
        }
    }
}
