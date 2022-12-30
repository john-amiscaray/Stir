package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks class properties as HTML attributes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Attribute {

    String name();
    String defaultValue() default "";

}
