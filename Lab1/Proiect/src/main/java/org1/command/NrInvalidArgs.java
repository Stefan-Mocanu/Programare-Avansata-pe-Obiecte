package org1.command;

public class NrInvalidArgs extends RuntimeException{
    String nume;
    NrInvalidArgs(String n){
        super();
        nume = n;
    }
    @Override
    public String getMessage() {
        return ("Utilizare invalida a "+nume+"!");
    }
}
