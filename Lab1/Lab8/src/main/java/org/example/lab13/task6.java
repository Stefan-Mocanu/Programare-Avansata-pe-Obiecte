package org.example.lab13;

import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class task6 {
    static int total = 0;
    static char chr;
    private static Lock lock = new ReentrantLock();
    static class Calculeaza extends Thread{
        String linie;
        Calculeaza(String linie){
            this.linie = linie;
        }
        public void run(){
            int nr =0;
            for(int i=0;i<linie.length();i++){
                if(chr == linie.charAt(i))nr++;
            }
            lock.lock();
            try{
                total += nr;
            }finally {
                lock.unlock();
            }

        }
    }
    public static void main(String[] args) {
        String path = args[0];
        String caracter = args[1]; // nu se specifica in cerinta de unde se obtine
        chr = caracter.charAt(0);
        List<Calculeaza> thr = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(path))){
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                thr.add(new Calculeaza(sc.next()));
                thr.get(thr.size()-1).start();
            }
            for(Calculeaza c : thr){
                c.join();
            }
            System.out.println(total);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
