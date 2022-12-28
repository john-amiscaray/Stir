package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.util.ElementProcessor;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "style")
@Data
public class Style extends AbstractUIElement{

    @InnerContent(encode = false)
    private StringBuilder css = new StringBuilder();

    private final ElementProcessor processor = ElementProcessor.getInstance();

    @Builder
    public Style(String id, @Singular List<String> cssClasses, String style,
                 @Singular List<CssRule> rules, @Singular List<String> literalCssStrings) {
        super(id, cssClasses, style);
        for (String cssString : literalCssStrings) {
            this.css.append(cssString);
        }
        for (CssRule rule : rules) {
            this.css.append(processor.processStyle(rule));
        }
    }

    public Style(String styles){

        css.append(styles);

    }

    public void addRule(CssRule rule){

        String old = css.toString();
        css.append(processor.processStyle(rule));
        propertyChangeSupport.firePropertyChange("css", new StringBuilder(old), css);

    }

    public void addStylesAsRawString(String styles){

        String old = css.toString();
        if(!css.isEmpty()){
            css.append("\n");
        }
        css.append(styles);
        propertyChangeSupport.firePropertyChange("css", new StringBuilder(old), css);

    }

}
