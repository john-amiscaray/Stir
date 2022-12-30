package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a list item
 */
@HTMLElement(tagName = "li")
public class ListItem extends AbstractUIElement{

    /**
     * The inner content of the list item
     */
    @InnerContent
    private final String content;

    public ListItem(String id, List<String> cssClasses, String style, String content) {
        super(id, cssClasses, style);
        this.content = content;
    }

    public ListItem(String content) {
        this.content = content;
    }

    public static ListItemBuilder builder() {
        return new ListItemBuilder();
    }


    public String getContent() {
        return this.content;
    }

    public String toString() {
        return "ListItem(content=" + this.getContent() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ListItem)) return false;
        final ListItem other = (ListItem) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ListItem;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public static class ListItemBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String content;

        ListItemBuilder() {
        }

        public ListItemBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ListItemBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public ListItemBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public ListItemBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public ListItemBuilder style(String style) {
            this.style = style;
            return this;
        }

        public ListItemBuilder content(String content) {
            this.content = content;
            return this;
        }

        public ListItem build() {
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

            return new ListItem(id, cssClasses, style, content);
        }

        public String toString() {
            return "ListItem.ListItemBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", content=" + this.content + ")";
        }
    }
}
