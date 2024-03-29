package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.util.ElementProcessor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A pojo representing a style element
 */
@HTMLElement(tagName = "style")
@NoArgsConstructor
public class Style extends AbstractUIElement{

    /**
     * The inner CSS
     */
    @InnerContent(encode = false)
    private StringBuilder css = new StringBuilder();

    private final ElementProcessor processor = ElementProcessor.getInstance();

    public Style(String id, List<String> cssClasses, String style,
                 List<CssRule> rules, List<String> literalCssStrings, Map<String, String> customAttributes) {
        super(id, cssClasses, style, false, customAttributes);
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

    public static StyleBuilder builder() {
        return new StyleBuilder();
    }

    /**
     * Adds a CSS rule to the style
     * @param rule the {@link CssRule CSSRule} to add to the style
     */
    public void addRule(CssRule rule){

        String old = css.toString();
        css.append(processor.processStyle(rule));
        propertyChangeSupport.firePropertyChange("css", new StringBuilder(old), css);

    }

    /**
     * Adds CSS as a raw string
     * @param styles A string with the styles to add
     */
    public void addStylesAsRawString(String styles){

        String old = css.toString();
        if(!css.isEmpty()){
            css.append("\n");
        }
        css.append(styles);
        propertyChangeSupport.firePropertyChange("css", new StringBuilder(old), css);

    }

    public StringBuilder getCss() {
        return this.css;
    }

    public ElementProcessor getProcessor() {
        return this.processor;
    }

    public void setCss(StringBuilder css) {
        this.css = css;
    }

    public String toString() {
        return "Style(css=" + this.getCss() + ", processor=" + this.getProcessor() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Style)) return false;
        final Style other = (Style) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$css = this.getCss();
        final Object other$css = other.getCss();
        if (this$css == null ? other$css != null : !this$css.equals(other$css)) return false;
        final Object this$processor = this.getProcessor();
        final Object other$processor = other.getProcessor();
        if (this$processor == null ? other$processor != null : !this$processor.equals(other$processor)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Style;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $css = this.getCss();
        result = result * PRIME + ($css == null ? 43 : $css.hashCode());
        final Object $processor = this.getProcessor();
        result = result * PRIME + ($processor == null ? 43 : $processor.hashCode());
        return result;
    }

    public static class StyleBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private ArrayList<CssRule> rules;
        private ArrayList<String> literalCssStrings;
        private ArrayList<String> customAttributes$key;
        private ArrayList<String> customAttributes$value;

        StyleBuilder() {
        }

        public StyleBuilder id(String id) {
            this.id = id;
            return this;
        }

        public StyleBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public StyleBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public StyleBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public StyleBuilder style(String style) {
            this.style = style;
            return this;
        }

        public StyleBuilder rule(CssRule rule) {
            if (this.rules == null) this.rules = new ArrayList<CssRule>();
            this.rules.add(rule);
            return this;
        }

        public StyleBuilder rules(Collection<? extends CssRule> rules) {
            if (this.rules == null) this.rules = new ArrayList<CssRule>();
            this.rules.addAll(rules);
            return this;
        }

        public StyleBuilder clearRules() {
            if (this.rules != null)
                this.rules.clear();
            return this;
        }

        public StyleBuilder literalCssString(String literalCssString) {
            if (this.literalCssStrings == null) this.literalCssStrings = new ArrayList<String>();
            this.literalCssStrings.add(literalCssString);
            return this;
        }

        public StyleBuilder literalCssStrings(Collection<? extends String> literalCssStrings) {
            if (this.literalCssStrings == null) this.literalCssStrings = new ArrayList<String>();
            this.literalCssStrings.addAll(literalCssStrings);
            return this;
        }

        public StyleBuilder clearLiteralCssStrings() {
            if (this.literalCssStrings != null)
                this.literalCssStrings.clear();
            return this;
        }

        public StyleBuilder customAttribute(String customAttributeKey, String customAttributeValue) {
            if (this.customAttributes$key == null) {
                this.customAttributes$key = new ArrayList<String>();
                this.customAttributes$value = new ArrayList<String>();
            }
            this.customAttributes$key.add(customAttributeKey);
            this.customAttributes$value.add(customAttributeValue);
            return this;
        }

        public StyleBuilder customAttributes(Map<? extends String, ? extends String> customAttributes) {
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

        public StyleBuilder clearCustomAttributes() {
            if (this.customAttributes$key != null) {
                this.customAttributes$key.clear();
                this.customAttributes$value.clear();
            }
            return this;
        }

        public Style build() {
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
            List<CssRule> rules;
            switch (this.rules == null ? 0 : this.rules.size()) {
                case 0:
                    rules = java.util.Collections.emptyList();
                    break;
                case 1:
                    rules = java.util.Collections.singletonList(this.rules.get(0));
                    break;
                default:
                    rules = java.util.Collections.unmodifiableList(new ArrayList<CssRule>(this.rules));
            }
            List<String> literalCssStrings;
            switch (this.literalCssStrings == null ? 0 : this.literalCssStrings.size()) {
                case 0:
                    literalCssStrings = java.util.Collections.emptyList();
                    break;
                case 1:
                    literalCssStrings = java.util.Collections.singletonList(this.literalCssStrings.get(0));
                    break;
                default:
                    literalCssStrings = java.util.Collections.unmodifiableList(new ArrayList<String>(this.literalCssStrings));
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

            return new Style(id, cssClasses, style, rules, literalCssStrings, customAttributes);
        }

        public String toString() {
            return "Style.StyleBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", rules=" + this.rules + ", literalCssStrings=" + this.literalCssStrings + ", customAttributes$key=" + this.customAttributes$key + ", customAttributes$value=" + this.customAttributes$value + ")";
        }
    }
}
