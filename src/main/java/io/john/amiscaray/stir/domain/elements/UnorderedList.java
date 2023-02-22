package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing an unordered list
 */
@HTMLElement(tagName = "ul")
@NoArgsConstructor
public class UnorderedList extends AbstractListElement{

    public UnorderedList(String id, List<String> cssClasses, String style, List<ListItem> listItems, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, listItems, hidden, customAttributes);
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
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

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

        public UnorderedListBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public UnorderedListBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public UnorderedListBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
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

            return new UnorderedList(id, cssClasses, style, listItems, hidden, customAttributes);
        }

        public String toString() {
            return "UnorderedList.UnorderedListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", listItems=" + this.listItems + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
