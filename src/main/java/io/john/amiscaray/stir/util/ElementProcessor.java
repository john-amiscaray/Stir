package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.*;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.annotation.exceptions.InvalidClassListException;
import io.john.amiscaray.stir.annotation.exceptions.InvalidObjectTableException;
import io.john.amiscaray.stir.domain.elements.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class responsible for generating the appropriate HTML markup
 */
public class ElementProcessor {

    private static ElementProcessor instance;

    /**
     * The indentation size (in spaces) for the document
     */
    @Getter
    @Setter
    private static int indentationSize = 4;

    /**
     * An option to disable caching globally
     */
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

    /**
     * Gets the HTML tag for some class representing an HTML element
     * @param clazz The given class
     * @return The HTML tag for the class
     */
    public String getTagName(Class<?> clazz){

        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if(annotation.annotationType().equals(HTMLElement.class)){
                return ((HTMLElement) annotation).tagName();
            }
        }
        return null;

    }

    /**
     * Retrieves the fields of a given class along with its superclasses up to (but not including) CacheableElement or Object
     * @param type The given class
     * @return The fields that make up the class and its superclasses up to (but not including) CacheableElement or Object
     */
    public Field[] getAllFields(Class<?> type){

        Field[] declared = type.getDeclaredFields();
        Class<?> superClazz = type.getSuperclass();
        Field[] superFields = superClazz.equals(Object.class) || superClazz.equals(CacheableElement.class) ?
                new Field[0] : getAllFields(superClazz);
        Field[] fields = new Field[declared.length + superFields.length];
        Arrays.setAll(fields, i ->
                (i < superFields.length ? superFields[i] : declared[i - superFields.length]));
        return fields;

    }

    /**
     * Generates the HTML markup of a list of elements
     * @param elements The list of elements
     * @param indentationLevel Base indentation level to use for the elements
     * @return The final HTML markup of the elements
     */
    public String getMarkupForElementList(List<? extends AbstractUIElement> elements, int indentationLevel){

        return String.format("%s".repeat(elements.size()),
                elements.stream()
                        .map(element -> {

                            if(element instanceof Table){
                                return getMarkup((Table) element).indent(ElementProcessor.getIndentationSize() * indentationLevel);
                            }
                            return getMarkup(element).indent(ElementProcessor.getIndentationSize() * indentationLevel);
                        })
                        .toArray()).stripTrailing();

    }

    /**
     * Builds the opening tag for an HTML element, putting it in the StringBuilder passed to the method
     * @param builder The String builder for the entire HTML content of the element
     * @param obj The object to build the opening tag for
     * @param elMeta The HTMLElement meta for the element
     * @param tagName The tag name of the element
     * @throws IllegalAccessException From the Java reflection API
     */
    private void buildElementOpeningTag(StringBuilder builder, AbstractUIElement obj, HTMLElement elMeta, String tagName) throws IllegalAccessException {

        Class<?> clazz = obj.getClass();
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
                if(f.getType().equals(Boolean.class)){
                    assert value instanceof Boolean;
                    if((Boolean) value){
                        builder.append(" ").append(meta.name());
                    }
                }else{
                    builder.append(" ").append(meta.name()).append("=\"").append(encode(value.toString())).append("\"");
                }
            }else if(f.isAnnotationPresent(Id.class)){
                if(value == null){
                    continue;
                }
                builder.append(" id=\"").append(encode(value.toString())).append("\"");
            }else if(f.isAnnotationPresent(ClassList.class)){
                if(!(value instanceof List)){
                    throw new InvalidClassListException();
                }
                if(((List<?>) value).isEmpty()){
                    continue;
                }
                builder.append(" class=\"");
                try{
                    StringBuilder styleClassBuilder = new StringBuilder();
                    for (String styleClazz : (List<String>) value) {
                        styleClassBuilder.append(styleClazz).append(" ");
                    }
                    builder.append(styleClassBuilder.toString().trim()).append("\"");
                }catch (ClassCastException ex){
                    throw new InvalidClassListException();
                }
            }
        }

        builder.append(">");
        if(elMeta.newLineAfterOpening()){
            builder.append("\n");
        }

    }

    /**
     * Generates the closing tag of an element
     * @param builder The StringBuilder for the HTML content of the element
     * @param tagName The tag name of the element
     */
    private void buildElementClosingTag(StringBuilder builder, String tagName){

        builder.append("</")
                .append(tagName)
                .append(">");

    }

    /**
     * Builds a label for the HTML element
     * @param builder The StringBuilder for the HTML content to be generated
     * @param label The label field of the object
     * @param parent The object containing the label field
     * @throws IllegalAccessException From the Java Reflection API
     */
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

    /**
     * Retrieves the HTML content of an element using its cache
     * @param element The element to get the cached markup from
     * @return The Markup of the element
     */
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
                            List<AbstractUIElement> children = (List<AbstractUIElement>) value;
                            childContent.addAll(children.stream()
                                    .map(this::getMarkup)
                                    .map(String::stripTrailing)
                                    .collect(Collectors.toList()));
                        }else if(field.isAnnotationPresent(Nested.class)){
                            childContent.add(getMarkup((AbstractUIElement) value).stripTrailing());
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

    /**
     * The markup of the entries within a table represented by a collection
     * @param collection The table entries
     * @param type The type of the entries of the collection
     * @return The markup of the table entries
     */
    private String getInnerTableMarkup(Collection<?> collection, Class<?> type){

        StringBuilder builder = new StringBuilder();
        builder.append("""
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
            builder.append(String.format("<th>%s</th>", name).indent(ElementProcessor.indentationSize * 2));
        }
        builder.append("</tr>\n".indent(ElementProcessor.indentationSize));
        builder.append("</thead>\n");
        builder.append("<tbody>\n");
        try{
            for (Object obj : collection) {
                builder.append("<tr>\n".indent(ElementProcessor.indentationSize));
                for (Field field : fields) {
                    Object value = field.get(type.cast(obj));
                    builder.append(String.format("<td>%s</td>", value).indent(ElementProcessor.indentationSize * 2));
                }
                builder.append("</tr>".indent(ElementProcessor.indentationSize));
            }
        }catch (IllegalAccessException ex){
            ex.printStackTrace();
        }
        builder.append("</tbody>\n");
        return builder.toString();

    }

    /**
     * Retrieves the markup of a collection as a table
     * @param collection The table entries
     * @param type The type of the entries of the collection
     * @return The markup of the table entries
     */
    @Deprecated
    public String getMarkup(Collection<?> collection, Class<?> type){

        return """
                <table>
                """ +
                getInnerTableMarkup(collection, type).indent(ElementProcessor.indentationSize) +
                """
                </table>
                """;

    }

    /**
     * Generates markup for a Table element
     * @param table The table element
     * @return The markup of the table element
     */
    public String getMarkup(Table table){

        Collection<?> collection = table.getEntries();
        Class<?> clazz = table.getClazz();
        StringBuilder builder = new StringBuilder();
        HTMLElement meta = Table.class.getAnnotation(HTMLElement.class);
        try {
            buildElementOpeningTag(builder, table, meta, meta.tagName());
            builder.append(getInnerTableMarkup(collection, clazz).indent(ElementProcessor.indentationSize));
            buildElementClosingTag(builder, meta.tagName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return builder.toString();

    }

    /**
     * Generates markup for an HTML element
     * @param obj The element to generate markup for
     * @return The HTML string of the element
     */
    public String getMarkup(AbstractUIElement obj){

        boolean cacheEnabled = false;

        if(!obj.isCacheDisabled() && !ElementProcessor.cacheDisabled){
            String result = getMarkupFromCache(obj);
            if(result != null && !result.isEmpty()){
                return result;
            }
            cacheEnabled = true;
        }

        String tagName = getTagName(obj.getClass());

        if(tagName == null){
            throw new IllegalElementException("Argument passed to getMarkup is not a valid element");
        }
        Class<?> type = obj.getClass();
        tagName += (type.equals(Heading.class) ? ((Heading) obj).getLevel() : "");
        StringBuilder builder = new StringBuilder();
        StringBuilder cacheBuilder = new StringBuilder();
        boolean hasChildren = false;

        try{
            Field[] fields = getAllFields(type);
            HTMLElement elementMeta = type.getAnnotation(HTMLElement.class);

            for(Field field: fields){
                field.setAccessible(true);
                boolean hasAnnotation = field.isAnnotationPresent(Label.class);
                boolean isNotNull = field.get(obj) != null;
                if(hasAnnotation && isNotNull){
                    buildLabel(builder, field, obj);
                    if(cacheEnabled){
                        buildLabel(cacheBuilder, field, obj);
                    }
                }
            }
            buildElementOpeningTag(builder, obj, elementMeta, tagName);
            if(cacheEnabled){
                buildElementOpeningTag(cacheBuilder, obj, elementMeta, tagName);
            }
            for (Field field: fields) {
                Object value = field.get(obj);
                if(field.isAnnotationPresent(Nested.class)){
                    if(value == null){
                        continue;
                    }
                    hasChildren = true;
                    builder.append(getMarkup((AbstractUIElement) value).indent(ElementProcessor.indentationSize));
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
                    List<AbstractUIElement> children = (List<AbstractUIElement>) value;
                    for (AbstractUIElement child : children) {
                        builder.append(getMarkup(child).indent(ElementProcessor.indentationSize));
                        if(cacheEnabled){
                            cacheBuilder.append("%s".indent(ElementProcessor.indentationSize));
                        }
                    }
                }else if(field.isAnnotationPresent(InnerContent.class)){
                    InnerContent content = field.getAnnotation(InnerContent.class);
                    if(!field.getType().equals(String.class) && !field.getType().equals(StringBuilder.class)){
                        throw new IllegalElementException("A field annotated with @InnerContent must be a String or StringBuilder");
                    }
                    String finalContent = value != null ? value.toString().indent(ElementProcessor.indentationSize) : content.defaultValue();
                    finalContent = content.encode() ? encode(finalContent) : finalContent;
                    builder.append(finalContent);
                    if(cacheEnabled){
                        cacheBuilder.append(finalContent);
                    }
                }else if(field.isAnnotationPresent(TableEntries.class)){
                    if(value == null){
                        continue;
                    }else if(!(value instanceof Collection)){
                        throw new InvalidObjectTableException(value.getClass());
                    }else if(((Collection<?>) value).isEmpty()){
                        continue;
                    }
                    Collection<?> collection = ((Collection<?>) value);
                    Class<?> clazz = collection.toArray()[0].getClass();
                    String tableMarkup = getInnerTableMarkup(collection, clazz).indent(ElementProcessor.indentationSize);
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

    /**
     * Generates CSS content from a CssRule object
     * @param rule The object to generate CSS from
     * @return The CSS content corresponding to the object
     */
    public String processStyle(CssRule rule){

        StringBuilder template = new StringBuilder("""
                %s {
                """);
        List<String> formatArgs = new ArrayList<>();

        formatArgs.add(encodeForStringFormats(rule.getSelector()));

        for (CssRule nestedRule : rule.getNestedRules()) {
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

    /**
     * Converts a Collection to a table Element TODO deprecate this
     * @param collection The collection to convert to a table
     * @param clazz The type of the elements of the collection
     * @param <T> The type of the elements of the collection
     * @return A new table instance
     */
    @Deprecated
    public <T> Table collectionToTableElement(Collection<T> collection, Class<T> clazz){

        return new Table(collection, clazz);

    }

    /**
     * Encodes a string for HTML elements
     * @param dirty The string to encode
     * @return The HTML entity encoded string
     */
    public String encodeForEntitiesOnly(String dirty){

        return StringEscapeUtils.escapeHtml4(dirty);

    }

    /**
     * Encodes a string to escape % characters used for string formats and HTML entities
     * @param dirty The string to encode
     * @return The encoded string
     */
    public String encode(String dirty){

        return StringEscapeUtils.escapeHtml4(dirty.replaceAll("%", "%%"));

    }

    /**
     * Encodes a string for % characters used for string formates
     * @param dirty The string to encode
     * @return The encoded string
     */
    public String encodeForStringFormats(String dirty){

        return dirty.replaceAll("%", "%%");

    }

    /**
     * Unescapes the % characters in a String
     * @param encoded The encoded string
     * @return The unescaped string
     */
    public String unescapeStringFormats(String encoded){
        
        return encoded.replaceAll("%%", "%");
        
    }

}
