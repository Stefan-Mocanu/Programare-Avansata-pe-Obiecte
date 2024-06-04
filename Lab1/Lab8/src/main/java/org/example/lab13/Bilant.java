package org.example.lab13;

import java.util.ArrayList;
import java.util.List;

public class Bilant implements Comparable<Bilant>{
    private int positive,negative;
    private int getDifference(){
        return positive-negative;
    }
    public Bilant(int positive,int negative){
        this.positive = positive;
        this.negative = negative;
    }
    public Bilant(){
        this(0,0);
    }
    @Override
    public int compareTo(Bilant o) {
        return getDifference() - o.getDifference();
    }

    @Override
    public String toString() {
        return positive + " - " + negative;
    }

    public static void main(String[] args) {
        List<Bilant> obj = new ArrayList<>();
        obj.add(new Bilant(7,2));
        obj.add(new Bilant());
        obj.add(new Bilant(1,2));
        obj.stream().sorted().forEach(System.out::println);
    }
}
