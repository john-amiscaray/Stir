package io.john.amiscaray.stir.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a property as the inner content of the element
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InnerContent {

    String defaultValue() default "";

    /**
     * Whether the content should be HTML entity encoded
     * @return the boolean value
     */
    boolean encode() default true;

}
