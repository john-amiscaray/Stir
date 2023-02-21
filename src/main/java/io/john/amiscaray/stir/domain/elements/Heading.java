package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * A pojo representing a heading element (i.e. h1, h2, h3, etc.)
 */
@HTMLElement(tagName = "h")
@NoArgsConstructor
public class Heading extends AbstractTextElement{

    /**
     * The heading level (i.e. h1, h2, h3, etc.)
     */
    private Integer level;

    public Heading(String id, List<String> cssClasses, String style, String content, Integer level, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, content, hidden, customAttributes);
        this.level = level;
    }

    public Heading(Integer level, String content){
        super(null, new ArrayList<>(), null, content, false, new LinkedHashMap<>());
        this.level = level;
    }

    public static HeadingBuilder builder() {
        return new HeadingBuilder();
    }

    public void setLevel(Integer level) {
        propertyChangeSupport.firePropertyChange("level", this.level, level);
        this.level = level;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Heading)) return false;
        final Heading other = (Heading) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$level = this.level;
        final Object other$level = other.level;
        if (this$level == null ? other$level != null : !this$level.equals(other$level)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Heading;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $level = this.level;
        result = result * PRIME + ($level == null ? 43 : $level.hashCode());
        return result;
    }

    public String toString() {
        return "Heading(level=" + this.level + ")";
    }

    public Integer getLevel() {
        return this.level;
    }

    public static class HeadingBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String content;
        private Integer level;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        HeadingBuilder() {
        }

        public HeadingBuilder id(String id) {
            this.id = id;
            return this;
        }

        public HeadingBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public HeadingBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public HeadingBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public HeadingBuilder style(String style) {
            this.style = style;
            return this;
        }

        public HeadingBuilder content(String content) {
            this.content = content;
            return this;
        }

        public HeadingBuilder level(Integer level) {
            this.level = level;
            return this;
        }

        public HeadingBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public HeadingBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public HeadingBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public HeadingBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Heading build() {
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

            return new Heading(id, cssClasses, style, content, level, hidden, customAttributes);
        }

        public String toString() {
            return "Heading.HeadingBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", content=" + this.content + ", level=" + this.level + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
