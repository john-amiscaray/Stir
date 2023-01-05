package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A pojo representing a list of nav links
 */
@HTMLElement(tagName = "ul")
@NoArgsConstructor
public class NavLinkList extends AbstractUIElement{

    /**
     * The inner {@link NavLink nav links }
     */
    @ChildList
    private List<NavLink> navLinks = new ArrayList<>();

    public NavLinkList(String id, List<String> cssClasses, String style, List<NavLink> navLinks) {
        super(id, cssClasses, style);
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

            return new NavLinkList(id, cssClasses, style, navLinks);
        }

        public String toString() {
            return "NavLinkList.NavLinkListBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", navLinks=" + this.navLinks + ")";
        }
    }
}
