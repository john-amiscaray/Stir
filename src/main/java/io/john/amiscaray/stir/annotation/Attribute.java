package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Attribute {

    String name();
    String defaultValue();

}
