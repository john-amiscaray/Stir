package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * A pojo representing a paragraph element
 */
@HTMLElement(tagName = "p")
@NoArgsConstructor
public class Paragraph extends AbstractTextElement {

    public Paragraph(String id, List<String> cssClasses, String style, String content, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, content, hidden, customAttributes);
    }

    public Paragraph(String content){

        super(null, new ArrayList<>(), null, content, false, new TreeMap<>());

    }

    public static ParagraphBuilder builder() {
        return new ParagraphBuilder();
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Paragraph)) return false;
        final Paragraph other = (Paragraph) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Paragraph;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }

    public static class ParagraphBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String content;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        ParagraphBuilder() {
        }

        public ParagraphBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ParagraphBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public ParagraphBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public ParagraphBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public ParagraphBuilder style(String style) {
            this.style = style;
            return this;
        }

        public ParagraphBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ParagraphBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public ParagraphBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public ParagraphBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public ParagraphBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Paragraph build() {
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

            return new Paragraph(id, cssClasses, style, content, hidden, customAttributes);
        }

        public String toString() {
            return "Paragraph.ParagraphBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", content=" + this.content + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
