package io.john.amiscaray.util;

import io.john.amiscaray.annotation.Attribute;
import io.john.amiscaray.annotation.HTMLElement;
import io.john.amiscaray.annotation.exceptions.IllegalElementException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ElementProcessor {

    private static ElementProcessor instance;

    private ElementProcessor(){}

    public static ElementProcessor getInstance() {
        if (instance == null) {
            instance = new ElementProcessor();
        }
        return instance;
    }

    public String getTagName(Class clazz){

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if(annotation.annotationType().equals(HTMLElement.class)){
                return ((HTMLElement) annotation).tagName();
            }
        }
        return null;

    }

    private void buildElementOpeningTag(StringBuilder builder, Object obj) throws IllegalAccessException {

        Class clazz = obj.getClass();
        String tagName = getTagName(clazz);
        builder.append("<")
                .append(tagName);

        Field[] fields = clazz.getDeclaredFields();
        for (Field f: fields) {
            if(f.isAnnotationPresent(Attribute.class)){
                f.setAccessible(true);
                Object value = f.get(obj);
                if(value == null){
                    value = f.getAnnotation(Attribute.class).defaultValue();
                }
                builder.append(" ").append(f.getName()).append("=\"").append(value).append("\"");
            }
        }

        builder.append(">\n");

    }

    private void buildElementClosingTag(StringBuilder builder, String tagName){

        builder.append("\n</")
                .append(tagName)
                .append(">");

    }

    public String getMarkup(Object obj){

        String tagName = getTagName(obj.getClass());

        if(tagName == null){
            throw new IllegalElementException("Argument passed to getMarkup is not an element");
        }
        Class type = obj.getClass();
        StringBuilder builder = new StringBuilder();

        try{
            buildElementOpeningTag(builder, obj);

            buildElementClosingTag(builder, tagName);

        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }

        return builder.toString();

    }

}
