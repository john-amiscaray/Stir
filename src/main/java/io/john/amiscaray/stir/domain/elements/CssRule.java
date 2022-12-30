package io.john.amiscaray.stir.domain.elements;

import java.util.*;

/**
 * A pojo representing a CSS rule
 */
public class CssRule {

    /**
     * The CSS selector for the rule
     */
    private final String selector;
    /**
     * A map representing the property and value of each CSS style
     */
    private Map<String, String> styles;
    /**
     * A list of nested CSS rules (used for things like media queries and CSS animations which have nested structures)
     */
    private List<CssRule> nestedRules;

    public CssRule(String selector, Map<String, String> styles, List<CssRule> nestedRules) {
        this.selector = selector;
        this.styles = styles;
        this.nestedRules = nestedRules;
    }

    public static CssRuleBuilder builder() {
        return new CssRuleBuilder();
    }

    public String getSelector() {
        return this.selector;
    }

    public Map<String, String> getStyles() {
        return this.styles;
    }

    public List<CssRule> getNestedRules() {
        return this.nestedRules;
    }

    public void setStyles(Map<String, String> styles) {
        this.styles = styles;
    }

    public void setNestedRules(List<CssRule> nestedRules) {
        this.nestedRules = nestedRules;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CssRule)) return false;
        final CssRule other = (CssRule) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$selector = this.getSelector();
        final Object other$selector = other.getSelector();
        if (this$selector == null ? other$selector != null : !this$selector.equals(other$selector)) return false;
        final Object this$styles = this.getStyles();
        final Object other$styles = other.getStyles();
        if (this$styles == null ? other$styles != null : !this$styles.equals(other$styles)) return false;
        final Object this$nestedRules = this.getNestedRules();
        final Object other$nestedRules = other.getNestedRules();
        if (this$nestedRules == null ? other$nestedRules != null : !this$nestedRules.equals(other$nestedRules))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CssRule;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $selector = this.getSelector();
        result = result * PRIME + ($selector == null ? 43 : $selector.hashCode());
        final Object $styles = this.getStyles();
        result = result * PRIME + ($styles == null ? 43 : $styles.hashCode());
        final Object $nestedRules = this.getNestedRules();
        result = result * PRIME + ($nestedRules == null ? 43 : $nestedRules.hashCode());
        return result;
    }

    public String toString() {
        return "CssRule(selector=" + this.getSelector() + ", styles=" + this.getStyles() + ", nestedRules=" + this.getNestedRules() + ")";
    }

    public static class CssRuleBuilder {
        private String selector;
        private ArrayList<String> styles$key;
        private ArrayList<String> styles$value;
        private ArrayList<CssRule> nestedRules;

        CssRuleBuilder() {
        }

        public CssRuleBuilder selector(String selector) {
            this.selector = selector;
            return this;
        }

        public CssRuleBuilder style(String styleKey, String styleValue) {
            if (this.styles$key == null) {
                this.styles$key = new ArrayList<String>();
                this.styles$value = new ArrayList<String>();
            }
            this.styles$key.add(styleKey);
            this.styles$value.add(styleValue);
            return this;
        }

        public CssRuleBuilder styles(Map<? extends String, ? extends String> styles) {
            if (this.styles$key == null) {
                this.styles$key = new ArrayList<String>();
                this.styles$value = new ArrayList<String>();
            }
            for (final Map.Entry<? extends String, ? extends String> $lombokEntry : styles.entrySet()) {
                this.styles$key.add($lombokEntry.getKey());
                this.styles$value.add($lombokEntry.getValue());
            }
            return this;
        }

        public CssRuleBuilder clearStyles() {
            if (this.styles$key != null) {
                this.styles$key.clear();
                this.styles$value.clear();
            }
            return this;
        }

        public CssRuleBuilder nestedRule(CssRule nestedRule) {
            if (this.nestedRules == null) this.nestedRules = new ArrayList<CssRule>();
            this.nestedRules.add(nestedRule);
            return this;
        }

        public CssRuleBuilder nestedRules(Collection<? extends CssRule> nestedRules) {
            if (this.nestedRules == null) this.nestedRules = new ArrayList<CssRule>();
            this.nestedRules.addAll(nestedRules);
            return this;
        }

        public CssRuleBuilder clearNestedRules() {
            if (this.nestedRules != null)
                this.nestedRules.clear();
            return this;
        }

        public CssRule build() {
            Map<String, String> styles;
            switch (this.styles$key == null ? 0 : this.styles$key.size()) {
                case 0:
                    styles = Collections.emptyMap();
                    break;
                case 1:
                    styles = Collections.singletonMap(this.styles$key.get(0), this.styles$value.get(0));
                    break;
                default:
                    styles = new LinkedHashMap<String, String>(this.styles$key.size() < 1073741824 ? 1 + this.styles$key.size() + (this.styles$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.styles$key.size(); $i++)
                        styles.put(this.styles$key.get($i), (String) this.styles$value.get($i));
                    styles = Collections.unmodifiableMap(styles);
            }
            List<CssRule> nestedRules;
            switch (this.nestedRules == null ? 0 : this.nestedRules.size()) {
                case 0:
                    nestedRules = Collections.emptyList();
                    break;
                case 1:
                    nestedRules = Collections.singletonList(this.nestedRules.get(0));
                    break;
                default:
                    nestedRules = Collections.unmodifiableList(new ArrayList<CssRule>(this.nestedRules));
            }

            return new CssRule(selector, styles, nestedRules);
        }

        public String toString() {
            return "CssRule.CssRuleBuilder(selector=" + this.selector + ", styles$key=" + this.styles$key + ", styles$value=" + this.styles$value + ", nestedRules=" + this.nestedRules + ")";
        }
    }
}
