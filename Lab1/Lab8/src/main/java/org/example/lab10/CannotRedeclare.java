package org.example.lab10;

public class CannotRedeclare extends RuntimeException{
    @Override
    public String getMessage() {
        return "Instances cannot be redeclared";
    }
}
