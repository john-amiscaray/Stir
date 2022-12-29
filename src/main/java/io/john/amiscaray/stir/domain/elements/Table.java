package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.TableEntries;

import java.util.Collection;

@HTMLElement(tagName = "table")
public class Table extends AbstractUIElement {

    @TableEntries
    private Collection<?> entries;

    private Class<?> clazz;

    public Table(Collection<?> entries, Class<?> clazz) {
        this.entries = entries;
        this.clazz = clazz;
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
}
