package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing an ordered list element
 */
@HTMLElement(tagName = "ol")
@NoArgsConstructor
public class OrderedList extends AbstractListElement {

    public OrderedList(String id, List<String> cssClasses, String style, List<ListItem> listItems, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, listItems, hidden, customAttributes);
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
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

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

        public OrderedListBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public OrderedListBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public OrderedListBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public OrderedListBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
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

            return new OrderedList(id, cssClasses, style, listItems, hidden, customAttributes);
        }

        public String toString() {
            return "OrderedList.OrderedListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", listItems=" + this.listItems + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
