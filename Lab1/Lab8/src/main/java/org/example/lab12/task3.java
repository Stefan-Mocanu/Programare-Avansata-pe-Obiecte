package org.example.lab12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class task3 {
    private static void createDirectory(File dir) throws IOException{
        if(!dir.exists()){
            if(!dir.mkdir()){
                throw new IOException("Nu s-a putut crea folderul: "+ dir.getAbsolutePath());
            }
        }
    }
    private static void createHeadFile(File headF) throws IOException{
        try (FileWriter writer = new FileWriter(headF)) {
            writer.write("ref: refs/heads/main\n");
        }
    }
    public static void main(String[] args) {
        if(args.length!=1){
            throw new IllegalArgumentException();
        }
        String dirPath = args[0];
        File gitDirectory = new File(dirPath, ".git");
        File objectsDirectory = new File(gitDirectory, "objects");
        File refsDirectory = new File(gitDirectory, "refs");
        File headFile = new File(gitDirectory, "HEAD");
        try{
            createDirectory(gitDirectory);
            createDirectory(objectsDirectory);
            createDirectory(refsDirectory);
            createHeadFile(headFile);
            System.out.println("Initialized empty Git repo in "+gitDirectory.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error initializing git repo: " +e.getMessage());
            e.printStackTrace();
        }
    }
}
