package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks List properties of a class as a List of child elements
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ChildList {

}
