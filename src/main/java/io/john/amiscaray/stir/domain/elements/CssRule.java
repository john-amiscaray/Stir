package io.john.amiscaray.stir.domain.elements;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class CssRule {

    private final String selector;
    private Map<String, String> styles;
    private List<CssRule> nested;

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder{

        private String selector;
        private final Map<String, String> styles;
        private final List<CssRule> nested;

        public Builder(){
            styles = new LinkedHashMap<>();
            nested = new ArrayList<>();
        }

        public Builder selector(String selector){
            this.selector = selector;
            return this;
        }

        public Builder addStyle(String property, String value){
            styles.put(property, value);
            return this;
        }

        public Builder addNested(CssRule rule){
            nested.add(rule);
            return this;
        }

        public CssRule build(){
            return new CssRule(selector, styles, nested);
        }

    }

}
