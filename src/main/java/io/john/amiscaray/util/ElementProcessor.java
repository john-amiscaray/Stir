package io.john.amiscaray.util;

import io.john.amiscaray.annotation.exceptions.IllegalElementException;

import java.lang.annotation.Annotation;
import java.util.Locale;

public class ElementProcessor {

    private static ElementProcessor instance;

    private ElementProcessor(){}

    public static ElementProcessor getInstance() {
        if (instance == null) {
            instance = new ElementProcessor();
        }
        return instance;
    }

    public Annotation getElementAnnotation(Class clazz){

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if(annotation.annotationType().getPackage().getName().equals("io.john.amiscaray.annotation.elements")){
                return annotation;
            }
        }
        return null;

    }

    private void buildElementOpeningTag(StringBuilder builder, Class elementType){

        builder.append("<")
                .append(elementType.getSimpleName().toLowerCase(Locale.ROOT))
                .append(">");

    }

    private void buildElementClosingTag(StringBuilder builder, Class elementType){

        builder.append("</")
                .append(elementType.getSimpleName().toLowerCase(Locale.ROOT))
                .append(">");

    }

    public String getMarkup(Object obj){

        Annotation elementType = getElementAnnotation(obj.getClass());

        if(elementType == null){
            throw new IllegalElementException("Argument passed to getMarkup is not an element");
        }
        Class type = obj.getClass();
        StringBuilder builder = new StringBuilder();

        buildElementOpeningTag(builder, elementType.annotationType());

        buildElementClosingTag(builder, elementType.annotationType());

        return builder.toString();

    }

}
