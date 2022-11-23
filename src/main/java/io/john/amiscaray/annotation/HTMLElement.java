package io.john.amiscaray.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface HTMLElement {

    String tagName();
    boolean hasClosing() default true;

}
