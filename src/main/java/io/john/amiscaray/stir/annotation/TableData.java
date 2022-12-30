package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Metadata about a property for use in a table
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface TableData {

    String columnName() default "";

}
