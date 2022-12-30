package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing an article element
 */
@HTMLElement(tagName = "article")
public class Article extends AbstractElementContainer<AbstractUIElement>{

    public Article(String id, List<String> cssClasses, String style, List<AbstractUIElement> children) {
        super(id, cssClasses, style, children);
    }

    public static ArticleBuilder builder() {
        return new ArticleBuilder();
    }

    public static class ArticleBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<AbstractUIElement> children;

        ArticleBuilder() {
        }

        public ArticleBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ArticleBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public ArticleBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public ArticleBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public ArticleBuilder style(String style) {
            this.style = style;
            return this;
        }

        public ArticleBuilder child(AbstractUIElement child) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.add(child);
            return this;
        }

        public ArticleBuilder children(Collection<? extends AbstractUIElement> children) {
            if (this.children == null) this.children = new ArrayList<AbstractUIElement>();
            this.children.addAll(children);
            return this;
        }

        public ArticleBuilder clearChildren() {
            if (this.children != null)
                this.children.clear();
            return this;
        }

        public Article build() {
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

            return new Article(id, cssClasses, style, children);
        }

        public String toString() {
            return "Article.ArticleBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", children=" + this.children + ")";
        }
    }
}
