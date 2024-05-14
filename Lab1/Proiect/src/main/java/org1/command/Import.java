package org1.command;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org1.Main;

public class Import implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if (arg.length != 3
                || !arg[0].equals("playlist")
                || (!arg[1].equals("CSV")
                && !arg[1].equals("JSON")
                && !arg[1].equals("STEVE"))) {
            throw new NrInvalidArgs("import playlist");
        }
        String nume="";
        List<String> id=new ArrayList<>();
        if(arg[1].equals("CSV")){
            try(CSVReader reader = new CSVReaderBuilder(new FileReader(arg[2])).build()){
                List<String[]> myEntries = reader.readAll();
                nume = myEntries.get(0)[0];
                for(String[] el:myEntries){
                    if(el[0].equals(nume))continue;
                    id.add(el[0]);
                }
            } catch (IOException | CsvException e) {
                throw new RuntimeException(e);
            }
        } else if (arg[1].equals("JSON")) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(arg[2]))
            {
                //Read JSON file
                Object obj = jsonParser.parse(reader);
                JSONObject raw = (JSONObject) obj;
                nume = (String) raw.get("nume");
                JSONArray songs = (JSONArray) raw.get("songs");
                songs.forEach(song -> id.add((String) ((JSONObject) song).get("id")));
            } catch (ParseException | IOException e) {
                throw new RuntimeException();
            }
        }else{
            try {
                File myObj = new File(arg[2]);
                Scanner myReader = new Scanner(myObj);
                if(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] raw = data.split("\\|");
                    nume = raw[0];
                    for(int i=1;i< raw.length;i++){
                        id.add(raw[i]);
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }
        }
        try{
        Main.usr.command("create playlist "+nume);
        Main.usr.command("add byName "+nume+" "+String.join(" ",id));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
