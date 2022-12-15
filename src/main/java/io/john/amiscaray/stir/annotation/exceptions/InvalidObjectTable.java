package io.john.amiscaray.stir.annotation.exceptions;

public class InvalidObjectTable extends RuntimeException{

    public InvalidObjectTable(Class<?> type) {
        super("Expect object table was type Collection but instead got: " + type.getName());
    }
}
