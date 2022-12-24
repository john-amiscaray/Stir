package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.*;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.annotation.exceptions.InvalidObjectTable;
import io.john.amiscaray.stir.domain.elements.CacheableElement;
import io.john.amiscaray.stir.domain.elements.CollectionTableAdapter;
import io.john.amiscaray.stir.domain.elements.CssRule;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ElementProcessor {

    private static ElementProcessor instance;
    @Getter
    @Setter
    private static int indentationSize = 4;

    @Getter
    @Setter
    private static boolean cacheDisabled = false;

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

    public Field[] getAllFields(Class<?> type){

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
                        .map(element -> {

                            if(element instanceof CollectionTableAdapter){
                                return getMarkup((CollectionTableAdapter) element).indent(ElementProcessor.getIndentationSize() * indentationLevel);
                            }
                            return getMarkup(element).indent(ElementProcessor.getIndentationSize() * indentationLevel);
                        })
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
                builder.append(" ").append(meta.name()).append("=\"").append(encode(value.toString())).append("\"");
            }else if(f.isAnnotationPresent(Id.class)){
                if(value == null){
                    continue;
                }
                builder.append(" id=\"").append(encode(value.toString())).append("\"");
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
                .append(encode(parentId))
                .append("\">\n")
                .append(encode(label.get(parent).toString()).indent(ElementProcessor.indentationSize))
                .append("</label>\n");

    }

    public String getMarkupFromCache(CacheableElement element) {

        if(element.getCacheStatus().equals(CacheableElement.CacheStatus.CLEAN)){
            if(!element.isHasChildren()){
                return element.getCacheContents();
            }else{
                try{
                    List<String> childContent = new ArrayList<>();
                    Class type = element.getClass();
                    Field[] fields = getAllFields(type);
                    for(Field field: fields){
                        field.setAccessible(true);
                        Object value = field.get(element);
                        if(field.isAnnotationPresent(ChildList.class)){
                            List<Object> children = (List<Object>) value;
                            childContent.addAll(children.stream()
                                    .map(this::getMarkup)
                                    .map(String::stripTrailing)
                                    .collect(Collectors.toList()));
                        }else if(field.isAnnotationPresent(Nested.class)){
                            childContent.add(getMarkup(value).stripTrailing());
                        }
                    }
                    return unescapeStringFormats(String.format(element.getCacheContents(), childContent.toArray()));
                }catch(IllegalAccessException ex){
                    ex.printStackTrace();
                }
            }
        }

        return null;

    }

    public String getMarkup(Collection<?> collection, Class<?> type){

        StringBuilder builder = new StringBuilder();
        builder.append("""
                <table>
                    <thead>
                        <tr>
                """);
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if(field.isAnnotationPresent(TableData.class)){
                String columnMetaName = field.getAnnotation(TableData.class).columnName();
                if(!columnMetaName.isBlank()){
                    name = columnMetaName;
                }
            }
            builder.append(String.format("<th>%s</th>", name).indent(ElementProcessor.indentationSize * 3));
        }
        builder.append("</tr>\n".indent(ElementProcessor.indentationSize * 2));
        builder.append("</thead>\n".indent(ElementProcessor.indentationSize));
        builder.append("<tbody>\n".indent(ElementProcessor.indentationSize));
        try{
            for (Object obj : collection) {
                builder.append("<tr>\n".indent(ElementProcessor.indentationSize * 2));
                for (Field field : fields) {
                    Object value = field.get(type.cast(obj));
                    builder.append(String.format("<td>%s</td>", value).indent(ElementProcessor.indentationSize * 3));
                }
                builder.append("</tr>".indent(ElementProcessor.indentationSize * 2));
            }
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }
        builder.append("""
                    </tbody>
                </table>
                """);
        return builder.toString();

    }

    public String getMarkup(CollectionTableAdapter adapter){

        Collection<?> collection = adapter.getCollection();
        Class<?> clazz = adapter.getClazz();
        return getMarkup(collection, clazz);

    }

    public String getMarkup(Object obj){

        boolean cacheEnabled = false;

        if(obj instanceof CacheableElement && !((CacheableElement) obj).isCacheDisabled() && !ElementProcessor.cacheDisabled){
            String result = getMarkupFromCache((CacheableElement) obj);
            if(result != null && !result.isEmpty()){
                return result;
            }
            cacheEnabled = true;
        }

        String tagName = getTagName(obj.getClass());

        if(tagName == null){
            throw new IllegalElementException("Argument passed to getMarkup is not a valid element");
        }
        Class type = obj.getClass();
        StringBuilder builder = new StringBuilder();
        StringBuilder cacheBuilder = new StringBuilder();
        boolean hasChildren = false;

        try{
            Field[] fields = getAllFields(type);
            HTMLElement elementMeta = (HTMLElement) type.getAnnotation(HTMLElement.class);

            for(Field field: fields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(Label.class) && field.get(obj) != null){
                    buildLabel(builder, field, obj);
                    if(cacheEnabled){
                        buildLabel(cacheBuilder, field, obj);
                    }
                }
            }
            buildElementOpeningTag(builder, obj, elementMeta);
            if(cacheEnabled){
                buildElementOpeningTag(cacheBuilder, obj, elementMeta);
            }
            for (Field field: fields) {
                Object value = field.get(obj);
                if(field.isAnnotationPresent(Nested.class)){
                    if(value == null){
                        continue;
                    }
                    hasChildren = true;
                    builder.append(getMarkup(value).indent(ElementProcessor.indentationSize));
                    if(cacheEnabled){
                        cacheBuilder.append("%s".indent(ElementProcessor.indentationSize));
                    }
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
                        if(cacheEnabled){
                            cacheBuilder.append("%s".indent(ElementProcessor.indentationSize));
                        }
                    }
                }else if(field.isAnnotationPresent(InnerContent.class)){
                    InnerContent content = field.getAnnotation(InnerContent.class);
                    String finalContent = value != null ? value.toString().indent(ElementProcessor.indentationSize) : content.defaultValue();
                    finalContent = content.encode() ? encode(finalContent) : finalContent;
                    builder.append(finalContent);
                    if(cacheEnabled){
                        cacheBuilder.append(finalContent);
                    }
                }else if(field.isAnnotationPresent(ObjectTable.class)){
                    if(value == null){
                        continue;
                    }else if(!(value instanceof Collection)){
                        throw new InvalidObjectTable(value.getClass());
                    }
                    Class<?> clazz = value.getClass();
                    String tableMarkup = getMarkup((Collection<?>) value, clazz).indent(ElementProcessor.indentationSize);
                    builder.append(tableMarkup);
                    if(cacheEnabled){
                        cacheBuilder.append(tableMarkup);
                    }
                }
            }
            if(elementMeta.hasClosing()){
                buildElementClosingTag(builder, tagName);
                if(cacheEnabled){
                    buildElementClosingTag(cacheBuilder, tagName);
                }
            }

        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }
        String finalMarkup = unescapeStringFormats(builder.toString());
        if(cacheEnabled){
            if(!hasChildren){
                ((CacheableElement) obj).setCacheContents(finalMarkup);
            }else{
                ((CacheableElement) obj).setCacheContents(cacheBuilder.toString());
            }
            ((CacheableElement) obj).setHasChildren(hasChildren);
        }
        return finalMarkup;

    }

    public String processStyle(CssRule rule){

        StringBuilder template = new StringBuilder("""
                %s {
                """);
        List<String> formatArgs = new ArrayList<>();

        formatArgs.add(encodeForStringFormats(rule.getSelector()));

        for (CssRule nestedRule : rule.getNested()) {
            String nested = processStyle(nestedRule);
            template.append(encodeForStringFormats(nested.indent(ElementProcessor.indentationSize)));
        }
        for (Map.Entry<String, String> entry: rule.getStyles().entrySet()) {
            template.append("%s: %s;\n".indent(ElementProcessor.getIndentationSize()));
            formatArgs.add(encodeForStringFormats(entry.getKey()));
            formatArgs.add(encodeForStringFormats(entry.getValue()));
        }
        template.append("}");

        return unescapeStringFormats(String.format(template.toString(), formatArgs.toArray()));

    }

    public <T> CollectionTableAdapter collectionToTableElement(Collection<T> collection, Class<T> clazz){

        return new CollectionTableAdapter(collection, clazz);

    }

    public String encodeForEntitiesOnly(String dirty){

        return StringEscapeUtils.escapeHtml4(dirty);

    }

    public String encode(String dirty){

        return StringEscapeUtils.escapeHtml4(dirty.replaceAll("%", "%%"));

    }

    public String encodeForStringFormats(String dirty){

        return dirty.replaceAll("%", "%%");

    }
    
    public String unescapeStringFormats(String encoded){
        
        return encoded.replaceAll("%%", "%");
        
    }

}
