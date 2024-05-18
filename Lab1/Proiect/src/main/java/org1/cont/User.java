package org1.cont;

import org1.command.Command;
import org1.command.NrInvalidArgs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstractizarea unui user
 * Fiecare mostenitor al acestei clase va avea comenzi permise si interzice, completand atributele interne
 */
abstract public class User {
    Map<String, Command> comenzi;
    Map<String, Boolean> comenzi_interzise;

    /**
     *
     * @param arg
     * Parametrul este despartit in cuvinte separata prin spatiu sau expresii delimitate de ""
     * @throws ComandaInexistenta in cazul in care nu exista comanda
     * @throws ComandaInterzisa in cazul in care userul nu are drepturile suficiente pentru a rula o comanda
     */
    public void command(String arg) {
        ArrayList<String> splited1 = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(arg);
        while (m.find())
            splited1.add(m.group(1).replace("\"", "")); // Add .replace("\"", "") to remove surrounding quotes.
        String[] splited = new String[splited1.size()];
        for(int i=0;i<splited1.size();i++){
            splited[i] = splited1.get(i);
        }
        int START = 1;
        if(splited[0].equals("create")){
            splited[0] = splited[0]+" "+splited[1];
            START =2;
        }

        if (comenzi.containsKey(splited[0])){
            try {
                comenzi.get(splited[0]).UseCommand(Arrays.copyOfRange(splited, START, splited.length));
            }catch (NrInvalidArgs e){
                System.out.println(e.getMessage());
                throw new RuntimeException();
            }
            return;
        }
        if (comenzi_interzise.containsKey(splited[0])){
            throw new ComandaInterzisa();
        }
        throw new ComandaInexistenta();
    }
}
