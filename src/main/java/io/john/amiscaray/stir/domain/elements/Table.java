package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.TableEntries;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a table element
 */
@HTMLElement(tagName = "table")
@NoArgsConstructor
public class Table extends AbstractUIElement {

    /**
     * A collection of objects representing the entries of the table
     */
    @TableEntries
    private Collection<?> entries;

    /**
     * The class of the elements of the {@link Table#entries entries collection}
     */
    private Class<?> clazz;

    public Table(String id, List<String> cssClasses, String style, Collection<?> entries, Class<?> clazz, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.entries = entries;
        this.clazz = clazz;
    }

    public Table(Collection<?> entries, Class<?> clazz) {
        this.entries = entries;
        this.clazz = clazz;
    }

    public static TableBuilder builder() {
        return new TableBuilder();
    }

    public Collection<?> getEntries() {
        return this.entries;
    }

    public Class<?> getClazz() {
        return this.clazz;
    }

    public void setEntries(Collection<?> entries) {
        this.entries = entries;
    }

    public void setClazz(Class<?> clazz) {
        propertyChangeSupport.firePropertyChange("clazz", this.clazz, clazz);
        this.clazz = clazz;
    }

    public String toString() {
        return "Table(entries=" + this.getEntries() + ", clazz=" + this.getClazz() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Table)) return false;
        final Table other = (Table) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$entries = this.getEntries();
        final Object other$entries = other.getEntries();
        if (this$entries == null ? other$entries != null : !this$entries.equals(other$entries)) return false;
        final Object this$clazz = this.getClazz();
        final Object other$clazz = other.getClazz();
        if (this$clazz == null ? other$clazz != null : !this$clazz.equals(other$clazz)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Table;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $entries = this.getEntries();
        result = result * PRIME + ($entries == null ? 43 : $entries.hashCode());
        final Object $clazz = this.getClazz();
        result = result * PRIME + ($clazz == null ? 43 : $clazz.hashCode());
        return result;
    }

    public static class TableBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<Object> entries;
        private Class<?> clazz;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        TableBuilder() {
        }

        public TableBuilder id(String id) {
            this.id = id;
            return this;
        }

        public TableBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public TableBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public TableBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public TableBuilder style(String style) {
            this.style = style;
            return this;
        }

        public TableBuilder entry(Object entry) {
            if (this.entries == null) this.entries = new ArrayList<Object>();
            this.entries.add(entry);
            return this;
        }

        public TableBuilder entries(Collection<?> entries) {
            if (this.entries == null) this.entries = new ArrayList<Object>();
            this.entries.addAll(entries);
            return this;
        }

        public TableBuilder clearEntries() {
            if (this.entries != null)
                this.entries.clear();
            return this;
        }

        public TableBuilder clazz(Class<?> clazz) {
            this.clazz = clazz;
            return this;
        }

        public TableBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public TableBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public TableBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public TableBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Table build() {
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
            Collection<Object> entries;
            switch (this.entries == null ? 0 : this.entries.size()) {
                case 0:
                    entries = java.util.Collections.emptyList();
                    break;
                case 1:
                    entries = java.util.Collections.singletonList(this.entries.get(0));
                    break;
                default:
                    entries = java.util.Collections.unmodifiableList(new ArrayList<Object>(this.entries));
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

            return new Table(id, cssClasses, style, entries, clazz, hidden, customAttributes);
        }

        public String toString() {
            return "Table.TableBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", entries=" + this.entries + ", clazz=" + this.clazz + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
