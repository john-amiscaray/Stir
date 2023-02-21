package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a section element
 */
@HTMLElement(tagName = "section")
@NoArgsConstructor
public class Section extends AbstractElementContainer<AbstractUIElement>{

    public Section(String id, List<String> cssClasses, String style, List<AbstractUIElement> children, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, children, hidden, customAttributes);
    }

    public static SectionBuilder builder() {
        return new SectionBuilder();
    }

    public static class SectionBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<AbstractUIElement> children;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        SectionBuilder() {
        }

        public SectionBuilder id(String id) {
            this.id = id;
            return this;
        }

        public SectionBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public SectionBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public SectionBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public SectionBuilder style(String style) {
            this.style = style;
            return this;
        }

        public SectionBuilder child(AbstractUIElement child) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.add(child);
            return this;
        }

        public SectionBuilder children(Collection<? extends AbstractUIElement> children) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.addAll(children);
            return this;
        }

        public SectionBuilder clearChildren() {
            if (this.children != null)
                this.children.clear();
            return this;
        }

        public SectionBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public SectionBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public SectionBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public SectionBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Section build() {
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
            List<AbstractUIElement> children;
            switch (this.children == null ? 0 : this.children.size()) {
                case 0:
                    children = java.util.Collections.emptyList();
                    break;
                case 1:
                    children = java.util.Collections.singletonList(this.children.get(0));
                    break;
                default:
                    children = java.util.Collections.unmodifiableList(new ArrayList<AbstractUIElement>(this.children));
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

            return new Section(id, cssClasses, style, children, hidden, customAttributes);
        }

        public String toString() {
            return "Section.SectionBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", children=" + this.children + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
