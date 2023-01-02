package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.TableEntries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a table element
 */
@HTMLElement(tagName = "table")
public class Table extends AbstractUIElement {

    /**
     * A collection of objects representing the entries of the table
     */
    @TableEntries
    private Collection<?> entries;

    /**
     * The class of the elements of the {@link Table#entries entries collection}
     */
    private final Class<?> clazz;

    public Table(String id, List<String> cssClasses, String style, Collection<?> entries, Class<?> clazz) {
        super(id, cssClasses, style);
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

            return new Table(id, cssClasses, style, entries, clazz);
        }

        public String toString() {
            return "Table.TableBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", entries=" + this.entries + ", clazz=" + this.clazz + ")";
        }
    }
}
