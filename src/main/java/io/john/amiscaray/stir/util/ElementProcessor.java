package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.*;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class ElementProcessor {

    private static ElementProcessor instance;
    @Getter
    private static int indentationSize = 4;

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
            f.setAccessible(true);
            Object value = f.get(obj);
            if(f.isAnnotationPresent(Attribute.class)){
                f.setAccessible(true);
                if(value == null){
                    value = f.getAnnotation(Attribute.class).defaultValue();
                }
                builder.append(" ").append(f.getName()).append("=\"").append(value).append("\"");
            }else if(f.isAnnotationPresent(Id.class)){
                if(value == null){
                    continue;
                }
                builder.append(" id=\"").append(value).append("\"");
            }
        }

        builder.append(">\n");

    }

    private void buildElementClosingTag(StringBuilder builder, String tagName){

        builder.append("</")
                .append(tagName)
                .append(">");

    }

    private void buildLabel(StringBuilder builder, Field label, Object parent) throws IllegalAccessException {

        String parentId = null;
        Field[] fields = parent.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if(f.isAnnotationPresent(Id.class)){
                parentId = f.get(parent).toString();
            }
        }
        if(parentId == null){
            throw new IllegalArgumentException("A labeled element must have an id");
        }
        builder.append("<label for=\"")
                .append(parentId)
                .append("\">\n")
                .append(label.get(parent).toString().indent(ElementProcessor.indentationSize))
                .append("</label>\n");

    }

    public String getMarkup(Object obj){

        String tagName = getTagName(obj.getClass());

        if(tagName == null){
            throw new IllegalElementException("Argument passed to getMarkup is not an element");
        }
        Class type = obj.getClass();
        StringBuilder builder = new StringBuilder();

        try{
            Field[] fields = type.getDeclaredFields();
            HTMLElement elementMeta = (HTMLElement) type.getAnnotation(HTMLElement.class);

            for(Field field: fields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(Label.class) && field.get(obj) != null){
                    buildLabel(builder, field, obj);
                }
            }
            buildElementOpeningTag(builder, obj);
            for (Field field: fields) {
                Object value = field.get(obj);
                if(value == null){
                    continue;
                }
                if(field.isAnnotationPresent(Nested.class)){
                    builder.append(getMarkup(value).indent(ElementProcessor.indentationSize));
                }else if(field.isAnnotationPresent(ChildList.class)){
                    if(!field.getType().equals(List.class)){
                        throw new IllegalElementException("A child list must be a List");
                    }
                    List<Object> children = (List<Object>) value;
                    for (Object child : children) {
                        builder.append(getMarkup(child).indent(ElementProcessor.indentationSize));
                    }
                }
            }
            if(elementMeta.hasClosing()){
                buildElementClosingTag(builder, tagName);
            }

        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }

        return builder.toString();

    }

}
