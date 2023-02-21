package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.Input;
import lombok.Builder;
import lombok.Singular;

import java.util.List;
import java.util.TreeMap;

@HTMLElement(tagName = "form")
public class FormWithChildList extends AbstractUIElement {

    @ChildList(childrenType = Input.class)
    private List<Input> fields;

    @Builder
    public FormWithChildList(String id, @Singular List<String> cssClasses, String style, @Singular List<Input> fields) {
        super(id, cssClasses, style, false, new TreeMap<>());
        this.fields = fields;
    }

}
