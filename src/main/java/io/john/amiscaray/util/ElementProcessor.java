package io.john.amiscaray.util;

import io.john.amiscaray.annotation.exceptions.IllegalElementException;

public class ElementProcessor {

    private static ElementProcessor instance;

    private ElementProcessor(){}

    public static ElementProcessor getInstance() {
        if (instance == null) {
            instance = new ElementProcessor();
        }
        return instance;
    }

    public boolean isValidElementType(Class clazz){

        return clazz.getPackage().getName().equals("io.john.amiscaray.annotation");

    }

    public String getMarkup(Object obj){

        if(!isValidElementType(obj.getClass())){
            throw new IllegalElementException("Argument passed to getMarkup is not an element");
        }
        StringBuilder builder = new StringBuilder();

        return builder.toString();

    }

}
