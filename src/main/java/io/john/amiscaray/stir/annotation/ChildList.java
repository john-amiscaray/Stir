package io.john.amiscaray.stir.annotation;

import io.john.amiscaray.stir.domain.elements.AbstractUIElement;

import java.lang.annotation.*;

/**
 * Marks List properties of a class as a List of child elements
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ChildList {

    /**
     * The type of the children of the element for type safety when initializing the element via element descriptor
     * @return The class defaults to AbstractUIElement
     */
    Class<? extends AbstractUIElement> childrenType() default AbstractUIElement.class;

}
