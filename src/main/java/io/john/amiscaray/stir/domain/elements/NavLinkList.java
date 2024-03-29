package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a list of nav links
 */
@HTMLElement(tagName = "ul")
@NoArgsConstructor
public class NavLinkList extends AbstractUIElement{

    /**
     * The inner {@link NavLink nav links }
     */
    @ChildList(childrenType = NavLink.class)
    private List<NavLink> navLinks = new ArrayList<>();

    public NavLinkList(String id, List<String> cssClasses, String style, List<NavLink> navLinks, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.navLinks = navLinks;
    }

    public NavLinkList(List<NavLink> navLinks) {
        this.navLinks = navLinks;
    }

    public static NavLinkListBuilder builder() {
        return new NavLinkListBuilder();
    }

    public void addNavLink(NavLink link){

        List<NavLink> old = new ArrayList<>(navLinks);
        navLinks.add(link);
        propertyChangeSupport.firePropertyChange("navLinks", old, navLinks);

    }

    public static class NavLinkListBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<NavLink> navLinks;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        NavLinkListBuilder() {
        }

        public NavLinkListBuilder id(String id) {
            this.id = id;
            return this;
        }

        public NavLinkListBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public NavLinkListBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public NavLinkListBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public NavLinkListBuilder style(String style) {
            this.style = style;
            return this;
        }

        public NavLinkListBuilder navLink(NavLink navLink) {
            if (this.navLinks == null) this.navLinks = new ArrayList<NavLink>();
            this.navLinks.add(navLink);
            return this;
        }

        public NavLinkListBuilder navLinks(Collection<? extends NavLink> navLinks) {
            if (this.navLinks == null) this.navLinks = new ArrayList<NavLink>();
            this.navLinks.addAll(navLinks);
            return this;
        }

        public NavLinkListBuilder clearNavLinks() {
            if (this.navLinks != null)
                this.navLinks.clear();
            return this;
        }

        public NavLinkListBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public NavLinkListBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public NavLinkListBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public NavLinkListBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public NavLinkList build() {
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
            List<NavLink> navLinks;
            switch (this.navLinks == null ? 0 : this.navLinks.size()) {
                case 0:
                    navLinks = java.util.Collections.emptyList();
                    break;
                case 1:
                    navLinks = java.util.Collections.singletonList(this.navLinks.get(0));
                    break;
                default:
                    navLinks = java.util.Collections.unmodifiableList(new ArrayList<NavLink>(this.navLinks));
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

            return new NavLinkList(id, cssClasses, style, navLinks, hidden, customAttributes);
        }

        public String toString() {
            return "NavLinkList.NavLinkListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", navLinks=" + this.navLinks + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
