package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing an hgroup element
 */
@HTMLElement(tagName = "hgroup")
@NoArgsConstructor
public class HGroup extends AbstractElementContainer<AbstractTextElement>{

    public HGroup(String id, List<String> cssClasses, String style, List<AbstractTextElement> children, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, children, hidden, customAttributes);
    }

    public static HGroupBuilder builder() {
        return new HGroupBuilder();
    }

    public static class HGroupBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<AbstractTextElement> children;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        HGroupBuilder() {
        }

        public HGroupBuilder id(String id) {
            this.id = id;
            return this;
        }

        public HGroupBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public HGroupBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public HGroupBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public HGroupBuilder style(String style) {
            this.style = style;
            return this;
        }

        public HGroupBuilder child(AbstractTextElement child) {
            if (this.children == null) this.children = new ArrayList<AbstractTextElement>();
            this.children.add(child);
            return this;
        }

        public HGroupBuilder children(Collection<? extends AbstractTextElement> children) {
            if (this.children == null) this.children = new ArrayList<AbstractTextElement>();
            this.children.addAll(children);
            return this;
        }

        public HGroupBuilder clearChildren() {
            if (this.children != null)
                this.children.clear();
            return this;
        }

        public HGroupBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public HGroupBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public HGroupBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public HGroupBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public HGroup build() {
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
            List<AbstractTextElement> children;
            switch (this.children == null ? 0 : this.children.size()) {
                case 0:
                    children = java.util.Collections.emptyList();
                    break;
                case 1:
                    children = java.util.Collections.singletonList(this.children.get(0));
                    break;
                default:
                    children = java.util.Collections.unmodifiableList(new ArrayList<AbstractTextElement>(this.children));
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

            return new HGroup(id, cssClasses, style, children, hidden, customAttributes);
        }

        public String toString() {
            return "HGroup.HGroupBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", children=" + this.children + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
