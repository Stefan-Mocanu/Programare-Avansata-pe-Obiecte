package lab7;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class task1si2 {
    static void metoda1(){
        String myPathToFile = "D:\\Faculatate\\Coduri\\ANUL2\\Programare-Avansata-pe-Obiecte\\Lab1\\lab1\\src\\lab7\\inputTask1.txt";
        try (FileInputStream fileInputStream = new FileInputStream(myPathToFile)){
            int toSkip = "Do not print this.".length()+2;
            fileInputStream.skip(toSkip);
            int deCitit = fileInputStream.available();
            byte[] aux = new byte[deCitit];
            char[] resp = new char[deCitit];
            fileInputStream.read(aux);
            for(int i=0;i<deCitit;i++){
                resp[i] = (char) aux[i];
            }
            System.out.println(resp);
        }catch(IOException x){
            System.out.println("Nu se poate accesa fisierul.");
        }
    }
    static void metoda2(){
        String myPathToFile = "D:\\Faculatate\\Coduri\\ANUL2\\Programare-Avansata-pe-Obiecte\\Lab1\\lab1\\src\\lab7\\inputTask1.txt";
        try (FileInputStream fileInputStream = new FileInputStream(myPathToFile)){
            char current=(char) fileInputStream.read();
            while(current!='\n'){
                current = (char) fileInputStream.read();
            }
            int len = fileInputStream.available();
            char[] resp = new char[len];
            for(int i=0;i<len;i++){
                resp[i] = (char) fileInputStream.read();
            }
            System.out.println(resp);
        }catch(IOException x){
            System.out.println("Nu se poate accesa fisierul.");
        }
    }
    static void task2(){
        String myPathToFile = "D:\\Faculatate\\Coduri\\ANUL2\\Programare-Avansata-pe-Obiecte\\Lab1\\lab1\\src\\lab7\\inputTask1.txt";
        char[] bufferForReading = new char[100];
        try (FileReader input = new FileReader(myPathToFile)) {
            input.skip("Do not print this.".length()+2);
            while (input.ready()) {
                input.read(bufferForReading);
                //System.out.println(Arrays.toString(bufferForReading));
                StringBuilder deAfisat = new StringBuilder(new String());
                for(int i =0;i<100;i++){
                    if(bufferForReading[i]=='\0')break;
                    deAfisat.append(bufferForReading[i]);
                }
                System.out.println(deAfisat);

            }

        }catch (IOException e){
            System.out.println("Nu se poate citi fisierul.");
        }
    }
    public static void main(String[] argv){
        System.out.println("Metoda1:");
        metoda1();
        System.out.println("Metoda2:");
        metoda2();
        System.out.println("Task2:");
        task2();
    }
}
