package io.john.amiscaray.stir.annotation;

import java.lang.annotation.*;

/**
 * Marks a class as an HTMLElement
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface HTMLElement {

    String tagName();

    /**
     * Whether the element has a closing tag
     * @return the boolean value
     */
    boolean hasClosing() default true;

    /**
     * Whether to start a new line after the opening tag
     * @return the boolean value
     */
    boolean newLineAfterOpening() default true;

}
