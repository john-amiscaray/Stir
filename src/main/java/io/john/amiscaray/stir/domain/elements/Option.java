package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.util.List;
import java.util.Locale;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "option")
@Data
public class Option extends AbstractUIElement{

    @Attribute(name = "value")
    private String value;

    @InnerContent
    private String optionName;

    public Option(String optionName) {
        this.optionName = optionName;
        this.value = optionName.toLowerCase(Locale.ROOT);
    }

    @Builder
    public Option(String id, @Singular List<String> cssClasses, String style, String value, String optionName) {
        super(id, cssClasses, style);
        this.value = value;
        this.optionName = optionName;
    }

    public Option(String value, String optionName) {
        this.value = value;
        this.optionName = optionName;
    }
}
