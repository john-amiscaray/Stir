package io.john.amiscaray.stir.annotation.exceptions;

public class InvalidClassListException extends RuntimeException{

    public InvalidClassListException(){

        super("A class list must be a list of strings");

    }

}
