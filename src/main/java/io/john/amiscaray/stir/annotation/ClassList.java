package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks a property of a List of Strings as a list of CSS classes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ClassList {



}
