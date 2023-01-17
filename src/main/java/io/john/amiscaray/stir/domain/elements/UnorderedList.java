package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing an unordered list
 */
@HTMLElement(tagName = "ul")
@NoArgsConstructor
public class UnorderedList extends AbstractListElement{

    public UnorderedList(String id, List<String> cssClasses, String style, List<ListItem> listItems, boolean hidden) {
        super(id, cssClasses, style, listItems, hidden);
    }

    public static UnorderedListBuilder builder() {
        return new UnorderedListBuilder();
    }

    public static class UnorderedListBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<ListItem> listItems;
        private boolean hidden;

        UnorderedListBuilder() {
        }

        public UnorderedListBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UnorderedListBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public UnorderedListBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public UnorderedListBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public UnorderedListBuilder style(String style) {
            this.style = style;
            return this;
        }

        public UnorderedListBuilder listItem(ListItem listItem) {
            if (this.listItems == null) this.listItems = new ArrayList<ListItem>();
            this.listItems.add(listItem);
            return this;
        }

        public UnorderedListBuilder listItems(Collection<? extends ListItem> listItems) {
            if (this.listItems == null) this.listItems = new ArrayList<ListItem>();
            this.listItems.addAll(listItems);
            return this;
        }

        public UnorderedListBuilder clearListItems() {
            if (this.listItems != null)
                this.listItems.clear();
            return this;
        }

        public UnorderedListBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public UnorderedList build() {
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
            List<ListItem> listItems;
            switch (this.listItems == null ? 0 : this.listItems.size()) {
                case 0:
                    listItems = java.util.Collections.emptyList();
                    break;
                case 1:
                    listItems = java.util.Collections.singletonList(this.listItems.get(0));
                    break;
                default:
                    listItems = java.util.Collections.unmodifiableList(new ArrayList<ListItem>(this.listItems));
            }

            return new UnorderedList(id, cssClasses, style, listItems, hidden);
        }

        public String toString() {
            return "UnorderedList.UnorderedListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", listItems=" + this.listItems + ", hidden=" + this.hidden + ")";
        }
    }
}
