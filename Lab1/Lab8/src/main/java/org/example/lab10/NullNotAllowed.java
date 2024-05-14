package org.example.lab10;

public class NullNotAllowed extends RuntimeException{
    @Override
    public String getMessage() {
        return "Null is not allowed as a parameter";
    }
}
