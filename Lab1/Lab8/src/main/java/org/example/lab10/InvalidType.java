package org.example.lab10;

public class InvalidType extends RuntimeException{
    @Override
    public String getMessage() {
        return "Invalid type for object";
    }
}
