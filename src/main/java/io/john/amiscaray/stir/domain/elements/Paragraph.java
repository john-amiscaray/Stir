package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@HTMLElement(tagName = "p")
public class Paragraph extends AbstractTextElement {

    public Paragraph(String id, List<String> cssClasses, String style, String content) {
        super(id, cssClasses, style, content);
    }

    public Paragraph(String content){

        super(null, new ArrayList<>(), null, content);

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

        public Paragraph build() {
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

            return new Paragraph(id, cssClasses, style, content);
        }

        public String toString() {
            return "Paragraph.ParagraphBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", content=" + this.content + ")";
        }
    }
}
