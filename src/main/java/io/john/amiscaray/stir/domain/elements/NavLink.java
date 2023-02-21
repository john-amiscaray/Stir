package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a navlink as a list item
 */
@HTMLElement(tagName = "li")
@NoArgsConstructor
public class NavLink extends AbstractUIElement{

    /**
     * The inner {@link Anchor anchor} within the list item
     */
    @Nested
    private Anchor a;

    public NavLink(String id, List<String> cssClasses, String style, Anchor a, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
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
        propertyChangeSupport.firePropertyChange("a", a, this.a);
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
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

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

        public NavLinkBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public NavLinkBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public NavLinkBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public NavLinkBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
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

            return new NavLink(id, cssClasses, style, a, hidden, customAttributes);
        }

        public String toString() {
            return "NavLink.NavLinkBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", a=" + this.a + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
