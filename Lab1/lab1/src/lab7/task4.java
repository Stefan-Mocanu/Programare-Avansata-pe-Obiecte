package lab7;

import java.util.Scanner;
import java.util.stream.IntStream;

public class task4 {
    public static void main(String[] argv){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        IntStream.range(0,n)
                .filter(x->x%2==0)
                .mapToObj(x->new tuple(x,x*x))
                .forEach(System.out::println);
    }
}
