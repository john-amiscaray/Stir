package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface HTMLElement {

    String tagName();
    boolean hasClosing() default true;
    boolean newLineAfterOpening() default true;

}
