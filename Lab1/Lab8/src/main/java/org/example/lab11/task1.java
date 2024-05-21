package org.example.lab11;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class task1 {
    static class CustomThread extends Thread {
        List<Integer> nr;
        CustomThread(List<Integer> nr1){
            super();
            nr = nr1;
        }
        @Override
        public void run() {
            for(Integer elem:nr){
                int patrat = elem*elem;
                System.out.println(elem+"^2="+patrat);
            }
        }
    }
    static List<Integer> numere = new ArrayList<>();
    static void Metoda1(){
        int numberThreads = Runtime.getRuntime().availableProcessors();
        List<CustomThread> cts = new ArrayList<>();
        for(int i=0;i<numberThreads;i++){
            int start, stop;
            start = numere.size()/numberThreads * i;
            stop = Math.min(numere.size()-1,
                            Math.max(numere.size()/numberThreads * (i+1),
                                    start+1));
            List<Integer> bucata = numere.subList(start,stop);
            if(!bucata.isEmpty()){
                CustomThread ct = new CustomThread(bucata);
                cts.add(ct);
                ct.start();
            }
        }
        for(CustomThread ct: cts){
            try{
                ct.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    static void Metoda2(){
        numere.parallelStream().forEach(elem ->
                System.out.println(elem+"^2="+(elem*elem))
        );
    }
    static void Metoda3(){
        for (Integer elem:numere) {
            CompletableFuture
                    .supplyAsync(() -> elem
                            + "^2="
                            + elem * elem)
                    .thenAccept(System.out::println);
        }
    }
    static void Metoda4(){
        for(Integer elem:numere){
            int patrat = elem*elem;
            System.out.println(elem+"^2="+patrat);
        }
    }
    public static void main(String[] args) {
        Random rand = new Random();
        Integer[] nrs = {10,1000,10000,10000000};
        String fileName = "src/main/java/org/example/lab11/task2.txt";
        long startTime,endTime;
        byte[] strToBytes;
        try(FileOutputStream outputStream = new FileOutputStream(fileName)){
            for(Integer nr:nrs){
                numere.clear();
                for(int i=0;i<nr;i++){
                    numere.add(rand.nextInt(10000));
                }
                startTime = System.currentTimeMillis();
                Metoda1();
                endTime = System.currentTimeMillis();
                strToBytes = ("Pentru "+nr +" elemente Metoda1 a rulat in "+(endTime-startTime)+" milisecunde\n").getBytes();
                outputStream.write(strToBytes);

                startTime = System.currentTimeMillis();
                Metoda2();
                endTime = System.currentTimeMillis();
                strToBytes = ("Pentru "+nr +" elemente Metoda2 a rulat in "+(endTime-startTime)+" milisecunde\n").getBytes();
                outputStream.write(strToBytes);

                startTime = System.currentTimeMillis();
                Metoda3();
                endTime = System.currentTimeMillis();
                strToBytes = ("Pentru "+nr +" elemente Metoda3 a rulat in "+(endTime-startTime)+" milisecunde\n").getBytes();
                outputStream.write(strToBytes);

                startTime = System.currentTimeMillis();
                Metoda4();
                endTime = System.currentTimeMillis();
                strToBytes = ("Pentru "+nr +" elemente Metoda4 a rulat in "+(endTime-startTime)+" milisecunde\n").getBytes();
                outputStream.write(strToBytes);
            }
        }catch (IOException e){
            e.getMessage();
        }
    }
}
