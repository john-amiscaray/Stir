package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.Heading;
import io.john.amiscaray.stir.domain.elements.exceptions.ElementInitializationException;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ElementDescriptorProcessor {

    public static final String DEFAULT_BASE_PACKAGE = "io.john.amiscaray.stir.domain.elements";
    /**
     * The base package to scan for elements to instantiate via element descriptor
     */
    private static String basePackage = DEFAULT_BASE_PACKAGE;

    public static void setBasePackage(String basePackage) {
        ElementDescriptorProcessor.basePackage = basePackage;
    }

    /**
     * Get the regex the describes the portion of the descriptor for the element's fields (other than id and css classes)
     * @return The regex
     */
    public static String getFieldDescriptorRegex(){

        String innerBracketRegex = "([^\\(\\)\\{\\}\\[\\]\\\"\\']*(\\\'.*\\\')?)*";
        return "^(\\[" + innerBracketRegex + "\\])?(\\('.*'\\))?(\\{.*\\})?$";

    }

    /**
     * Processes an element descriptor using the set base package
     * @param descriptor The element descriptor
     * @return The resulting element
     */
    public static AbstractUIElement element(String descriptor) {

        return element(descriptor, basePackage);

    }

    /**
     * Processes an element descriptor
     * @param descriptor The element descriptor
     * @param javaPackage The package to scan for the appropriate class
     * @return The resulting element
     */
    public static AbstractUIElement element(String descriptor, String javaPackage) {

        Reflections reflections = new Reflections(javaPackage, Scanners.SubTypes);
        Set<Class<? extends AbstractUIElement>> classes = reflections.getSubTypesOf(AbstractUIElement.class);
        // Partitions the descriptor before any brackets used for specifying the attributes, inner content, child elements, etc.
        String[] partitions = descriptor.split("((?=[\\[({]))", 2);
        String tagNameIdAndClasses = partitions[0];
        String fieldsDescriptor = partitions.length > 1 ? partitions[1] : "";
        validateTagNameClassesAndID(tagNameIdAndClasses);
        validateFieldsDescriptor(fieldsDescriptor);
        String originalTagName = tagNameIdAndClasses.split("[.#]", 2)[0];
        String tagName = originalTagName.matches("h\\d") ? "h" : originalTagName;

        List<Class<?>> possibleTypes = tagName.equals("h") ? List.of(Heading.class) : classes.stream()
                .filter(clazz -> {
                    if(Modifier.isAbstract(clazz.getModifiers())){
                        return false;
                    }
                    if(!clazz.isAnnotationPresent(HTMLElement.class)){
                        throw new IllegalElementException("The class: " + clazz.getName() + " must be annotated with @HTMLElement");
                    }
                    return clazz.getAnnotation(HTMLElement.class).tagName().equals(tagName);
                }).collect(Collectors.toList());
        if(possibleTypes.isEmpty() || originalTagName.equals("h")){
            throw new DescriptorFormatException("No such element found");
        }
        RuntimeException lastError = null;
        for (Class<?> elementType : possibleTypes) {
            try{
                Constructor<?> emptyConstructor = ReflectionUtils.getConstructors(elementType, constructor -> constructor.getParameterTypes().length == 0)
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> { throw new ElementInitializationException("Missing an empty constructor to initialize the element"); });

                AbstractUIElement element = null;
                try {
                    element = (AbstractUIElement) emptyConstructor.newInstance();
                    List<String> cssClasses = getCSSClasses(tagNameIdAndClasses);
                    String id = getID(tagNameIdAndClasses);

                    element.setCssClasses(cssClasses);
                    element.setId(id);
                    String attributeDescriptor = "";
                    String innerContentDescriptor = "";
                    String childDescriptor = "";

                    Pattern pattern = Pattern.compile(getFieldDescriptorRegex());
                    Matcher matcher = pattern.matcher(fieldsDescriptor);

                    boolean foundMatch = matcher.find();
                    assert foundMatch;
                    if(matcher.group(1) != null){
                        attributeDescriptor = matcher.group(1);
                    }
                    if(matcher.group(4) != null){
                        innerContentDescriptor = matcher.group(4);
                    }
                    if(matcher.group(5) != null){
                        childDescriptor = matcher.group(5);
                    }
                    setElementAttributes(attributeDescriptor, element, elementType);
                    setElementInnerContent(innerContentDescriptor, element, elementType);
                    setElementChildren(childDescriptor, element, elementType, javaPackage);
                    if(elementType.equals(Heading.class)){
                        assert element.getClass().equals(Heading.class) && tagName.equals("h");
                        ((Heading) element).setLevel(Integer.parseInt(originalTagName.substring(1)));
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                    throw new ElementInitializationException("Unable to initialize element. Nested Exception is: " + e.getClass().getName() + ":\n" + e.getLocalizedMessage());
                }

                return element;
            }catch(ElementInitializationException|DescriptorFormatException ex){
                lastError = ex;
            }
        }
        assert lastError != null;
        throw lastError;

    }

    private static void validateTagNameClassesAndID(String descriptor){

        if(!descriptor.matches("[^\\s+\n]+")){
            throw new DescriptorFormatException("Whitespace is not allowed in tag names, css classes, or ids");
        }

    }

    private static void validateFieldsDescriptor(String fieldsDescriptor){

        if (fieldsDescriptor.isBlank()) {
            return;
        }

        if(!fieldsDescriptor.matches(getFieldDescriptorRegex())){
            throw new DescriptorFormatException("Malformed element descriptor. After the tag name, css classes, and id, there must be an attribute, inner content, and child descriptor in that order (each optional) where the attribute descriptor is enclosed in [], inner content (''), and child descriptor {}. Each of these blocks must not have nested brackets that are not enclosed in quotes.");
        }

    }

    private static void setElementAttributes(String fieldAttributeDescriptor, AbstractUIElement element, Class<?> elementInnerClass) throws IllegalAccessException {

        if(fieldAttributeDescriptor.isBlank()){
            return;
        }
        fieldAttributeDescriptor = fieldAttributeDescriptor.substring(1, fieldAttributeDescriptor.length() - 1);
        if(fieldAttributeDescriptor.isBlank()){
            return;
        }
        String[] settings = fieldAttributeDescriptor.split(",");
        for (String setting : settings) {
            if (!setting.matches("[^\\s=]+='.+'")) {
                throw new DescriptorFormatException("Malformed element descriptor attributes must follow the following regex: [^\\s=]+='.+'");
            }
            String[] keyValue = setting.split("=", 2);
            assert keyValue.length == 2;
            String value = keyValue[1];
            Field key = ReflectionUtils.getAllFields(elementInnerClass, field -> field.isAnnotationPresent(Attribute.class) &&
                            (field.getName().equals(keyValue[0]) || field.getAnnotation(Attribute.class).name().equals(keyValue[0]))).stream().findFirst()
                    .orElseThrow(() -> new DescriptorFormatException("Unknown Attribute: " + keyValue[0]));
            key.setAccessible(true);
            value = value.substring(1, value.length() - 1);
            if(key.getType().equals(Boolean.class)){
                if(!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")){
                    throw new DescriptorFormatException("Boolean attributes must have a value of either true or false");
                }
                key.set(element, Boolean.parseBoolean(value));
            }else if(key.getType().equals(Integer.class)){
                key.set(element, Integer.parseInt(value));
            }else if(key.getType().equals(Double.class)){
                key.set(element, Double.parseDouble(value));
            }else if(key.getType().equals(Long.class)){
                key.set(element, Long.parseLong(value));
            }else if(key.getType().equals(Float.class)){
                key.set(element, Float.parseFloat(value));
            }else{
                // Assume the thing is a String
                if(!key.getType().equals(String.class)){
                    throw new ElementInitializationException("Unsupported attribute type of: " + key.getType() + " for initialization with descriptors. The following types are supported: Boolean, Integer, Float, Double, Long, String.");
                }
                key.set(element, value);
            }
        }

    }

    private static void setElementChildren(String childDescriptor, AbstractUIElement element, Class<?> elementInnerClass, String javaPackage) throws IllegalAccessException, ClassNotFoundException {

        if(childDescriptor.isBlank()){
            return;
        }
        childDescriptor = childDescriptor.substring(1, childDescriptor.length() - 1);
        if(childDescriptor.isBlank()){
            return;
        }
        String[] childrenDescriptors = childDescriptor.split(",");
        Field childList = ReflectionUtils.getAllFields(elementInnerClass, field -> field.isAnnotationPresent(ChildList.class))
                .stream().findFirst().orElseThrow(() -> new ElementInitializationException("No element ChildList found. The element must have a List<? extends AbstractUIElement> field annotated with @ChildList"));
        childList.setAccessible(true);
        Class<? extends AbstractUIElement> childType = childList.getAnnotation(ChildList.class).childrenType();
        List children = new ArrayList<>();
        if(childList.getType() != List.class){
            throw new IllegalElementException("A field annotated with @ChildList must be of type List");
        }

        for (String descriptor : childrenDescriptors) {
            AbstractUIElement child = element(descriptor, javaPackage);
            try{
                children.add(childType.cast(child));
            }catch (ClassCastException ex){
                throw new DescriptorFormatException("Tried to add an element of type: " + child.getClass().getName() + " as a child when the parent only accepts " + childType.getName());
            }
        }

        childList.set(element, childList.getType().cast(children));

    }

    private static void setElementInnerContent(String innerContentDescriptor, AbstractUIElement element, Class<?> elementInnerClass) throws IllegalAccessException {

        if(innerContentDescriptor.isBlank()){
            return;
        }
        innerContentDescriptor = innerContentDescriptor.substring(2, innerContentDescriptor.length() - 2);
        if(innerContentDescriptor.isBlank()){
            return;
        }

        Field innerContentField = ReflectionUtils.getAllFields(elementInnerClass, field -> field.isAnnotationPresent(InnerContent.class))
                .stream().findFirst().orElseThrow(() -> new DescriptorFormatException("The element does not accept inner content. The element must have a field annotated with @InnerContent to do so."));

        innerContentField.setAccessible(true);

        if(innerContentField.getType().equals(StringBuilder.class)){
            innerContentField.set(element, new StringBuilder(innerContentDescriptor));
        }else if(innerContentField.getType().equals(String.class)){
            innerContentField.set(element, innerContentDescriptor);
        }else {
            throw new IllegalElementException("An field annotated with @InnerContent must be a String or a StringBuilder");
        }

    }

    private static List<String> getCSSClasses(String tagIdAndClassesDescriptor){

        List<String> cssClasses = new ArrayList<>();
        Pattern cssClassPattern = Pattern.compile("(?<=\\.)[^ .#]+");
        Matcher cssClassMatcher = cssClassPattern.matcher(tagIdAndClassesDescriptor);
        while(cssClassMatcher.find()){
            cssClasses.add(cssClassMatcher.group());
        }

        return cssClasses;

    }

    private static String getID(String tagIdAndClassesDescriptor){

        String id = null;
        Pattern idPattern = Pattern.compile("(?<=#)[^ .#]+");

        Matcher idPatternMatcher = idPattern.matcher(tagIdAndClassesDescriptor);

        int i = 0;
        while(idPatternMatcher.find()){
            if(i > 0){
                throw new DescriptorFormatException("Only 1 ID can be specified");
            }
            id = idPatternMatcher.group();
            i++;
        }

        return id;

    }

}
