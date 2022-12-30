package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks a property as the inner content of a label for the element
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Label {

    /**
     * The default text of the label
     * @return The string value
     */
    String defaultText();

}
