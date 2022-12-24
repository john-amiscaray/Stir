package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@HTMLElement(tagName = "ul")
@AllArgsConstructor
@NoArgsConstructor
public class NavLinkList extends AbstractUIElement{

    @ChildList
    private List<NavLink> navLinks = new ArrayList<>();

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder {

        private final NavLinkList navLinkList = new NavLinkList();

        public Builder addNavLink(NavLink link){

            navLinkList.navLinks.add(link);
            return this;

        }

        public Builder addNavLinkFromHrefAndLabel(String href, String label){

            navLinkList.navLinks.add(NavLink.fromLabelAndHref(href, label));
            return this;

        }

        public NavLinkList build(){

            return navLinkList;

        }

    }

}
