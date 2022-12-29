package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@HTMLElement(tagName = "ol")
public class OrderedList extends AbstractListElement {

    public OrderedList(String id, List<String> cssClasses, String style, List<ListItem> listItems) {
        super(id, cssClasses, style, listItems);
    }

    public static OrderedListBuilder builder() {
        return new OrderedListBuilder();
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderedList)) return false;
        final OrderedList other = (OrderedList) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderedList;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }

    public String toString() {
        return "OrderedList()";
    }

    public static class OrderedListBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<ListItem> listItems;

        OrderedListBuilder() {
        }

        public OrderedListBuilder id(String id) {
            this.id = id;
            return this;
        }

        public OrderedListBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public OrderedListBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public OrderedListBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public OrderedListBuilder style(String style) {
            this.style = style;
            return this;
        }

        public OrderedListBuilder listItem(ListItem listItem) {
            if (this.listItems == null) this.listItems = new ArrayList<ListItem>();
            this.listItems.add(listItem);
            return this;
        }

        public OrderedListBuilder listItems(Collection<? extends ListItem> listItems) {
            if (this.listItems == null) this.listItems = new ArrayList<ListItem>();
            this.listItems.addAll(listItems);
            return this;
        }

        public OrderedListBuilder clearListItems() {
            if (this.listItems != null)
                this.listItems.clear();
            return this;
        }

        public OrderedList build() {
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

            return new OrderedList(id, cssClasses, style, listItems);
        }

        public String toString() {
            return "OrderedList.OrderedListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", listItems=" + this.listItems + ")";
        }
    }
}
