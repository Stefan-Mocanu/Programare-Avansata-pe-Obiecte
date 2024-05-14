package org1.cont;

public class ComandaInterzisa extends RuntimeException{
    @Override
    public String getMessage() {
        return "Este interzis sa accesezi aceasta comanda!";
    }
}
