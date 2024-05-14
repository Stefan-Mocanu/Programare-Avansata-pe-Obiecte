package org.example.lab10;

public class NoInstance extends RuntimeException{
    @Override
    public String getMessage() {
        return "Cannot provide instance";
    }
}
