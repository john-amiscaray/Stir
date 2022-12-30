package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a navlink as a list item
 */
@HTMLElement(tagName = "li")
public class NavLink extends AbstractUIElement{

    /**
     * The inner {@link Anchor anchor} within the list item
     */
    @Nested
    private Anchor a;

    public NavLink(String id, List<String> cssClasses, String style, Anchor a) {
        super(id, cssClasses, style);
        this.a = a;
    }

    public NavLink(Anchor a) {
        this.a = a;
    }

    public static NavLink fromLabelAndHref(String label, String href){

        return new NavLink(new Anchor(label, href));

    }

    public static NavLinkBuilder builder() {
        return new NavLinkBuilder();
    }


    public Anchor getA() {
        return this.a;
    }

    public void setA(Anchor a) {
        this.a = a;
    }

    public String toString() {
        return "NavLink(a=" + this.getA() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NavLink)) return false;
        final NavLink other = (NavLink) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$a = this.getA();
        final Object other$a = other.getA();
        if (this$a == null ? other$a != null : !this$a.equals(other$a)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NavLink;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $a = this.getA();
        result = result * PRIME + ($a == null ? 43 : $a.hashCode());
        return result;
    }

    public static class NavLinkBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private Anchor a;

        NavLinkBuilder() {
        }

        public NavLinkBuilder id(String id) {
            this.id = id;
            return this;
        }

        public NavLinkBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public NavLinkBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public NavLinkBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public NavLinkBuilder style(String style) {
            this.style = style;
            return this;
        }

        public NavLinkBuilder a(Anchor a) {
            this.a = a;
            return this;
        }

        public NavLink build() {
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

            return new NavLink(id, cssClasses, style, a);
        }

        public String toString() {
            return "NavLink.NavLinkBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", a=" + this.a + ")";
        }
    }
}
