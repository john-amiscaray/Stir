package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing an hgroup element
 */
@HTMLElement(tagName = "hgroup")
public class HGroup extends AbstractElementContainer<AbstractTextElement>{

    public HGroup(String id, List<String> cssClasses, String style, List<AbstractTextElement> children) {
        super(id, cssClasses, style, children);
    }

    public static HGroupBuilder builder() {
        return new HGroupBuilder();
    }

    public static class HGroupBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<AbstractTextElement> children;

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

            return new HGroup(id, cssClasses, style, children);
        }

        public String toString() {
            return "HGroup.HGroupBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", children=" + this.children + ")";
        }
    }
}
