package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks a collection as a set of table entries
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface TableEntries {



}
