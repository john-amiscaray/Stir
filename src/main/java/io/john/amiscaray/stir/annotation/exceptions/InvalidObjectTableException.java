package io.john.amiscaray.stir.annotation.exceptions;

public class InvalidObjectTableException extends RuntimeException{

    public InvalidObjectTableException(Class<?> type) {
        super("Expect object table was type Collection but instead got: " + type.getName());
    }
}
