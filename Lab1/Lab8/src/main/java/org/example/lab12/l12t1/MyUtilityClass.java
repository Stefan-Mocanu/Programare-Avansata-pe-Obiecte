package org.example.lab12.l12t1;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

public class MyUtilityClass {
    public static <T> void printCollection(Collection<T> printThis) {
        if (printThis == null) throw new IllegalArgumentException();
        System.out.println(printThis);
    }

    public static <T, U> U aggregate(Collection<T> agg, U acc, BiFunction<U, T, U> fun) {
        if (agg == null || acc == null || fun == null) throw new IllegalArgumentException();
        for (T x : agg) {
            acc = fun.apply(acc, x);
        }
        return acc;
    }

    public static <T> void duplicateCollection(List<T> dup) {
        if (dup == null) throw new IllegalArgumentException();
        int size = dup.size();
        for (int i = 0; i < size; i++) {
            int index = i * 2 + 1;
            T obj = dup.get(i * 2);
            dup.add(index, obj);
        }
    }
}
