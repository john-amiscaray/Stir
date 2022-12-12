package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.*;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.CacheableElement;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
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

    public Field[] getAllFields(Class type){

        Field[] declared = type.getDeclaredFields();
        Field[] superFields = type.getSuperclass().getDeclaredFields();
        Field[] fields = new Field[declared.length + superFields.length];
        Arrays.setAll(fields, i ->
                (i < superFields.length ? superFields[i] : declared[i - superFields.length]));
        return fields;

    }

    public String getMarkupForElementList(List<?> elements, int indentationLevel){

        return String.format("%s".repeat(elements.size()),
                elements.stream()
                        .map(element -> getMarkup(element).indent(ElementProcessor.getIndentationSize() * indentationLevel))
                        .toArray()).stripTrailing();

    }

    private void buildElementOpeningTag(StringBuilder builder, Object obj, HTMLElement elMeta) throws IllegalAccessException {

        Class clazz = obj.getClass();
        String tagName = getTagName(clazz);
        builder.append("<")
                .append(tagName);

        Field[] fields = getAllFields(clazz);
        for (Field f: fields) {
            f.setAccessible(true);
            Object value = f.get(obj);
            if(f.isAnnotationPresent(Attribute.class)){
                f.setAccessible(true);
                Attribute meta = f.getAnnotation(Attribute.class);
                if(value == null){
                    value = meta.defaultValue();
                    if(meta.defaultValue().isEmpty()){
                        continue;
                    }
                }
                builder.append(" ").append(meta.name()).append("=\"").append(value).append("\"");
            }else if(f.isAnnotationPresent(Id.class)){
                if(value == null){
                    continue;
                }
                builder.append(" id=\"").append(value).append("\"");
            }
        }

        builder.append(">");
        if(elMeta.newLineAfterOpening()){
            builder.append("\n");
        }

    }

    private void buildElementClosingTag(StringBuilder builder, String tagName){

        builder.append("</")
                .append(tagName)
                .append(">");

    }

    private void buildLabel(StringBuilder builder, Field label, Object parent) throws IllegalAccessException {

        String parentId = null;
        Field[] fields = getAllFields(parent.getClass());
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

    public String getMarkup(CacheableElement element){

        if(element.getCacheStatus().equals(CacheableElement.CacheStatus.CLEAN)){
            return element.getCacheContents();
        }

        return getMarkup((Object) element);

    }

    public String getMarkup(Object obj){

        String tagName = getTagName(obj.getClass());

        if(tagName == null){
            throw new IllegalElementException("Argument passed to getMarkup is not an element");
        }
        Class type = obj.getClass();
        StringBuilder builder = new StringBuilder();
        boolean hasChildren = false;

        try{
            Field[] fields = getAllFields(type);
            HTMLElement elementMeta = (HTMLElement) type.getAnnotation(HTMLElement.class);

            for(Field field: fields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(Label.class) && field.get(obj) != null){
                    buildLabel(builder, field, obj);
                }
            }
            buildElementOpeningTag(builder, obj, elementMeta);
            for (Field field: fields) {
                Object value = field.get(obj);
                if(field.isAnnotationPresent(Nested.class)){
                    if(value == null){
                        continue;
                    }
                    hasChildren = true;
                    builder.append(getMarkup(value).indent(ElementProcessor.indentationSize));
                }else if(field.isAnnotationPresent(ChildList.class)){
                    if(value == null){
                        continue;
                    }
                    hasChildren = true;
                    if(!field.getType().equals(List.class)){
                        throw new IllegalElementException("A child list must be a List");
                    }
                    List<Object> children = (List<Object>) value;
                    for (Object child : children) {
                        builder.append(getMarkup(child).indent(ElementProcessor.indentationSize));
                    }
                }else if(field.isAnnotationPresent(InnerContent.class)){
                    InnerContent content = field.getAnnotation(InnerContent.class);
                    builder.append(value != null ? value.toString().indent(ElementProcessor.indentationSize) : content.defaultValue());
                }
            }
            if(elementMeta.hasClosing()){
                buildElementClosingTag(builder, tagName);
            }

        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }
        if(obj instanceof CacheableElement && !hasChildren){
            ((CacheableElement) obj).setCacheContents(builder.toString());
        }
        return builder.toString();

    }

}
