package io.john.amiscaray.stir.util;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import io.john.amiscaray.stir.domain.elements.exceptions.ElementInitializationException;
import io.john.amiscaray.stir.util.exceptions.DescriptorFormatException;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementDescriptorProcessor {

    public static AbstractUIElement element(String descriptor) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        return element(descriptor, "io.john.amiscaray.stir.domain.elements");

    }

    public static AbstractUIElement element(String descriptor, String javaPackage) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Reflections reflections = new Reflections(javaPackage, Scanners.SubTypes);
        Set<Class<? extends AbstractUIElement>> classes = reflections.getSubTypesOf(AbstractUIElement.class);
        // Partitions the descriptor before any brackets used for specifying the attributes, inner content, child elements, etc.
        String[] partitions = descriptor.split("[\\[({]", 2);
        String tagNameIdAndClasses = partitions[0];
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

        AbstractUIElement element = (AbstractUIElement) emptyConstructor.newInstance();
        List<String> cssClasses = getCSSClasses(tagNameIdAndClasses);
        String id = getID(tagNameIdAndClasses);

        element.setCssClasses(cssClasses);
        element.setId(id);
        return element;
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
