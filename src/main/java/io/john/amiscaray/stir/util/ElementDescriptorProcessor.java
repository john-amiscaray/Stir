package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.exceptions.ElementInitializationException;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementDescriptorProcessor {

    public static AbstractUIElement element(String descriptor) {

        return element(descriptor, "io.john.amiscaray.stir.domain.elements");

    }

    public static AbstractUIElement element(String descriptor, String javaPackage) {

        Reflections reflections = new Reflections(javaPackage, Scanners.SubTypes);
        Set<Class<? extends AbstractUIElement>> classes = reflections.getSubTypesOf(AbstractUIElement.class);
        // Partitions the descriptor before any brackets used for specifying the attributes, inner content, child elements, etc.
        String[] partitions = descriptor.split("((?=[\\[({]))", 2);
        String tagNameIdAndClasses = partitions[0];
        String fieldsDescriptor = partitions.length > 1 ? partitions[1] : "";
        validateFieldsDescriptor(fieldsDescriptor);
        String tagName = tagNameIdAndClasses.split("[.#]", 2)[0];
        Class<?> elementType = classes.stream()
                .filter(clazz -> {
                    if(Modifier.isAbstract(clazz.getModifiers())){
                        return false;
                    }
                    if(!clazz.isAnnotationPresent(HTMLElement.class)){
                        throw new IllegalElementException("The class: " + clazz.getName() + " must be annotated with @HTMLElement");
                    }
                    return clazz.getAnnotation(HTMLElement.class).tagName().equals(tagName);
                }).findFirst().orElseThrow(() -> new DescriptorFormatException("Unknown tag name of " + tagName + " in descriptor"));

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

            setElementAttributes(fieldsDescriptor, element, elementType);
            setElementInnerContent(fieldsDescriptor, element, elementType);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ElementInitializationException("Unable to initialize element. Nested Exception is: " + e.getClass().getName() + ":\n" + e.getLocalizedMessage());
        }

        return element;
    }

    private static void validateFieldsDescriptor(String fieldsDescriptor){

        if (fieldsDescriptor.isBlank()) {
            return;
        }

        String innerBracketRegex = "([^\\(\\)\\{\\}\\[\\]\\\"\\']*(\\\'.*\\\')?)*";

        if(!fieldsDescriptor.matches("(\\[" + innerBracketRegex + "\\])?(\\('.*'\\))?(\\{" + innerBracketRegex +"\\})?$")){
            throw new DescriptorFormatException("Malformed element descriptor. After the tag name, css classes, and id, there must be an attribute, inner content, and child descriptor in that order (each optional). Each of these blocks must not have nested brackets that are not enclosed in quotes.");
        }

    }

    private static void setElementAttributes(String fieldsDescriptor, AbstractUIElement element, Class<?> elementInnerClass) throws IllegalAccessException {

        Pattern attributeDescriptor = Pattern.compile("\\[.*\\]");
        Matcher attributeMatcher = attributeDescriptor.matcher(fieldsDescriptor);

        if(attributeMatcher.find()){

            String attributeDescriptorStr = attributeMatcher.group();
            attributeDescriptorStr = attributeDescriptorStr.substring(1, attributeDescriptorStr.length() - 1);
            if(attributeDescriptorStr.isBlank()){
                return;
            }
            String[] settings = attributeDescriptorStr.split(",");
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

    }

    private static void setElementInnerContent(String fieldsDescriptor, AbstractUIElement element, Class<?> elementInnerClass) throws IllegalAccessException {

        Pattern innerContentDescriptor = Pattern.compile("\\('.*'\\)");
        Matcher matcher = innerContentDescriptor.matcher(fieldsDescriptor);

        if(matcher.find()){

            String innerContentDescriptorStr = matcher.group();
            innerContentDescriptorStr = innerContentDescriptorStr.substring(2, innerContentDescriptorStr.length() - 2);
            if(innerContentDescriptorStr.isBlank()){
                return;
            }

            Field innerContentField = ReflectionUtils.getAllFields(elementInnerClass, field -> field.isAnnotationPresent(InnerContent.class))
                    .stream().findFirst().orElseThrow(() -> new DescriptorFormatException("The element does not accept inner content. The element must have a field annotated with @InnerContent to do so."));

            innerContentField.setAccessible(true);

            if(innerContentField.getType().equals(StringBuilder.class)){
                innerContentField.set(element, new StringBuilder(innerContentDescriptorStr));
            }else if(innerContentField.getType().equals(String.class)){
                innerContentField.set(element, innerContentDescriptorStr);
            }else{
                throw new IllegalElementException("An field annotated with @InnerContent must be a String or a StringBuilder");
            }

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
