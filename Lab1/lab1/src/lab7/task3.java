package lab7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class task3 {
    public static void main(String[] argv){
        for(Integer i=1;i<=10;i++){
            String  myPathToFile = String.format("D:\\Faculatate\\Coduri\\ANUL2\\Programare-Avansata-pe-Obiecte\\Lab1\\lab1\\src\\lab7\\fisiereTask3\\fisier_%s.txt",i);
            try{
                File fisier = new File(myPathToFile);
                if(fisier.createNewFile()){
                    try (FileWriter fileWriter = new FileWriter(fisier)){
                        fileWriter.write(i+"\n"+ LocalDateTime.now());
                    }catch (IOException e){
                        System.out.println("Fisierul nu poate fi accesat.");
                    }
                }else{
                    try (FileWriter fileWriter = new FileWriter(fisier)){
                        fileWriter.write(i+"\n"+ LocalDateTime.now());
                    }catch (IOException e){
                        System.out.println("Fisierul nu poate fi accesat.");
                    }
                }
            }catch (IOException e){
                System.out.println("Fisierul nu poate fi creat");
            }
        }
    }
}
