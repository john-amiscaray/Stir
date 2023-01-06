package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a footer element
 */
@HTMLElement(tagName = "footer")
@NoArgsConstructor
public class Footer extends AbstractElementContainer<AbstractUIElement>{

    public Footer(String id, List<String> cssClasses, String style, List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }

    public static FooterBuilder builder() {
        return new FooterBuilder();
    }

    public static class FooterBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<AbstractUIElement> children;

        FooterBuilder() {
        }

        public FooterBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FooterBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public FooterBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public FooterBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public FooterBuilder style(String style) {
            this.style = style;
            return this;
        }

        public FooterBuilder child(AbstractUIElement child) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.add(child);
            return this;
        }

        public FooterBuilder children(Collection<? extends AbstractUIElement> children) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.addAll(children);
            return this;
        }

        public FooterBuilder clearChildren() {
            if (this.children != null)
                this.children.clear();
            return this;
        }

        public Footer build() {
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

            return new Footer(id, cssClasses, style, children);
        }

        public String toString() {
            return "Footer.FooterBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", children=" + this.children + ")";
        }
    }
}
