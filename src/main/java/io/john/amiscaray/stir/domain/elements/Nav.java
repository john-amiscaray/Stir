package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@HTMLElement(tagName = "nav")
@AllArgsConstructor
@NoArgsConstructor
public class Nav extends AbstractUIElement{

    @Nested
    private NavLinkList list;

    public static Nav fromLabelHrefMap(LinkedHashMap<String, String> labelHref){

        LinkedHashMap<String, String> linkedMap = new LinkedHashMap<>(labelHref);
        Nav nav = new Nav();
        List<NavLink> links = linkedMap.entrySet().stream()
                .map(e -> NavLink.fromLabelAndHref(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        nav.list = new NavLinkList(links);

        return nav;

    }

}
