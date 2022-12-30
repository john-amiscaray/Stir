package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks a property as a nested HTML element
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Nested {



}
