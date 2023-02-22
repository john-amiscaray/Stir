package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks that a field in a class should be hidden when generating a table of instances of it
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface TableIgnore {

}
