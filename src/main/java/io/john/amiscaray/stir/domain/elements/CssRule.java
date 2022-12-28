package io.john.amiscaray.stir.domain.elements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class CssRule {

    private final String selector;
    @Singular
    private Map<String, String> styles;
    @Singular
    private List<CssRule> nestedRules;

}
