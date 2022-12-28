package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@HTMLElement(tagName = "ul")
@AllArgsConstructor
@NoArgsConstructor
public class NavLinkList extends AbstractUIElement{

    @ChildList
    private List<NavLink> navLinks = new ArrayList<>();

    @Builder
    public NavLinkList(String id, @Singular List<String> cssClasses, String style, @Singular List<NavLink> navLinks) {
        super(id, cssClasses, style);
        this.navLinks = navLinks;
    }

    public void addNavLink(NavLink link){

        List<NavLink> old = new ArrayList<>(navLinks);
        navLinks.add(link);
        propertyChangeSupport.firePropertyChange("navLinks", old, navLinks);

    }

}
