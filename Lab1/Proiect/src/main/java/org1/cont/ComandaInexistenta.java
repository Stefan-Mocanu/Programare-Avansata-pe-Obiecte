package org1.cont;

public class ComandaInexistenta extends RuntimeException{
    @Override
    public String getMessage() {
        return "Nu exista aceasta comanda";
    }
}
