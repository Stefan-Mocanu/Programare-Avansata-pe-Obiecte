package lab7;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class task5 {
    static private Random randomGenerator = new Random();
    static private ArrayList<Function<Double,Double>> op = new ArrayList<>();
    static Double applyRandomFunc(Double a){
        return op.get(randomGenerator.nextInt(op.size())).apply(a);
    }
    public static void main(String[] argv){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Double> lista= new ArrayList<>();
        for(int i =0;i<n;i++){
            String current = sc.next();
            try{
                double val = Double.parseDouble(current);
                lista.add(val);
            }catch(NumberFormatException e){
                i--;
                System.out.println("Input invalid.");
            }
        }
        op.add(x->x*x);
        op.add(x->x*(-1));
        op.add(x->1/x);
        op.add(Math::sqrt);
        op.add(Math::sin);
        lista.stream()
                .map(task5::applyRandomFunc)
                .forEach(System.out::println);
    }
}
