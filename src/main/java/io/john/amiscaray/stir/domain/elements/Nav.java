package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A pojo representing a nav element
 */
@HTMLElement(tagName = "nav")
@NoArgsConstructor
public class Nav extends AbstractUIElement{

    /**
     * The inner list of {@link NavLinkList nav links}
     */
    @Nested
    private NavLinkList list;

    public Nav(String id, List<String> cssClasses, String style, NavLinkList list, boolean hidden, Map<String, String> customAttributes) {
        super(id, cssClasses, style, hidden, customAttributes);
        this.list = list;
    }

    /**
     * Constructs a Nav from a map where the keys are the inner text of a nav link and the values are the hrefs
     * @param labelHref The map mentioned above
     * @return A new Nav instance
     */
    public static Nav fromLabelHrefMap(LinkedHashMap<String, String> labelHref){

        LinkedHashMap<String, String> linkedMap = new LinkedHashMap<>(labelHref);
        Nav nav = new Nav();
        List<NavLink> links = linkedMap.entrySet().stream()
                .map(e -> NavLink.fromLabelAndHref(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        nav.list = new NavLinkList(links);

        return nav;

    }

    public static NavBuilder builder() {
        return new NavBuilder();
    }

    /**
     * Adds a navlink to the nav
     * @param link The link to add to the nav
     */
    public void addNavLink(NavLink link){

        list.addNavLink(link);

    }

    public static class NavBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private NavLinkList list;
        private boolean hidden;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        NavBuilder() {
        }

        public NavBuilder id(String id) {
            this.id = id;
            return this;
        }

        public NavBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public NavBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public NavBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public NavBuilder style(String style) {
            this.style = style;
            return this;
        }

        public NavBuilder list(NavLinkList list) {
            this.list = list;
            return this;
        }

        public NavBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public NavBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public NavBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public NavBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Nav build() {
            List<String> cssClasses;
            switch (this.cssClasses == null ? 0 : this.cssClasses.size()) {
                case 0:
                    cssClasses = Collections.emptyList();
                    break;
                case 1:
                    cssClasses = Collections.singletonList(this.cssClasses.get(0));
                    break;
                default:
                    cssClasses = Collections.unmodifiableList(new ArrayList<String>(this.cssClasses));
            }
            Map<String, String> customAttributes;
            switch (this.customAttributes$key == null ? 0 : this.customAttributes$key.size()) {
                case 0:
                    customAttributes = Collections.emptyMap();
                    break;
                case 1:
                    customAttributes = Collections.singletonMap(this.customAttributes$key.get(0), this.customAttributes$value.get(0));
                    break;
                default:
                    customAttributes = new LinkedHashMap<String, String>(this.customAttributes$key.size() < 1073741824 ? 1 + this.customAttributes$key.size() + (this.customAttributes$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.customAttributes$key.size(); $i++)
                        customAttributes.put(this.customAttributes$key.get($i), (String) this.customAttributes$value.get($i));
                    customAttributes = Collections.unmodifiableMap(customAttributes);
            }

            return new Nav(id, cssClasses, style, list, hidden, customAttributes);
        }

        public String toString() {
            return "Nav.NavBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", list=" + this.list + ", hidden=" + this.hidden + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
