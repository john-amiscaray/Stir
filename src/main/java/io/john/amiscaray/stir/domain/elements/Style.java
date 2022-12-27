package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.util.ElementProcessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "style")
@Data
@NoArgsConstructor
public class Style extends AbstractUIElement{

    @InnerContent(encode = false)
    private StringBuilder css = new StringBuilder();

    private ElementProcessor processor = ElementProcessor.getInstance();

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

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Style style = new Style();

        public Builder addRule(CssRule rule){

            style.addRule(rule);
            return this;

        }

        public Builder addLiteralCss(String cssLiteral){

            style.addStylesAsRawString(cssLiteral);
            return this;

        }

        public Style build(){

            return style;

        }

    }

}
