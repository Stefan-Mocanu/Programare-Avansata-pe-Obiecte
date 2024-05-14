package org1.command;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org1.Main;
import org1.cont.UserAutentificat;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Export implements Command {
    @Override
    public void UseCommand(String[] arg) {
        if (arg.length != 4
                || !arg[0].equals("playlist")
                || (!arg[1].equals("byID")
                && !arg[1].equals("byName"))
                || (!arg[3].equals("CSV")
                && !arg[3].equals("JSON")
                && !arg[3].equals("STEVE"))) {
            throw new NrInvalidArgs("export playlist");
        }
        String user = ((UserAutentificat) Main.usr).getUsername();
        String id = arg[2];
        String nume = arg[2];
        List<String[]> deAdaugat = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            if (arg[1].equals("byName")) {
                String sqlStmt = """
                        select id
                        from playlist
                        where nume = ?
                        and user = ?
                        """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1, arg[1]);
                    preparedStatement.setString(2, user);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("Nu exista acest playlist: " + arg[1]);
                            throw new RuntimeException();
                        } else {
                            id = resultSet.getString("id");
                        }
                    }
                }
            } else {
                String sqlStmt = """
                        select nume
                        from playlist
                        where id = ?
                        and user = ?
                        """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, user);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("Nu exista acest playlist: " + arg[2]);
                            throw new RuntimeException();
                        } else {
                            nume = resultSet.getString("nume");
                        }
                    }
                }
            }
            String sqlStmt = """        
                    select name, author, an
                    from song,playlist_song
                    where id_playlist=?
                    and song.id = playlist_song.id_song
                    """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                preparedStatement.setString(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        deAdaugat.add(new String[]{resultSet.getString("name"),
                                resultSet.getString("author"),
                                resultSet.getString("an")});
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String data = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String fisier = "D:\\Faculatate\\Coduri\\ANUL2\\Programare-Avansata-pe-Obiecte\\Lab1\\Proiect\\src\\EXPORT\\export_"+user+"_"+nume+"_"+data;
        if (arg[3].equals("CSV")) {
            try {
                CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(fisier+".csv"))
                        .build();
                writer.writeNext(new String[]{nume});
                for (String[] entries : deAdaugat)
                    writer.writeNext(entries);
                writer.close();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }else if (arg[3].equals("JSON"))  {
            JSONArray songs = new JSONArray();
            for (String[] entries : deAdaugat){
                JSONObject song = new JSONObject();
                song.put("name",entries[0]);
                song.put("author",entries[1]);
                song.put("an",entries[2]);
                songs.add(song);
            }
            JSONObject export = new JSONObject();
            export.put("nume",nume);
            export.put("songs",songs);
            try (FileWriter file = new FileWriter(fisier+".json")) {
                file.write(export.toJSONString());
                file.flush();

            } catch (IOException e) {
                throw new RuntimeException();
            }
        }else{
            try (FileWriter file = new FileWriter(fisier+".txt")){
                StringBuilder deScris;
                deScris = new StringBuilder(nume);
                for (String[] entries : deAdaugat){
                    deScris.append("|")
                            .append(entries[0])
                            .append("*")
                            .append(entries[1])
                            .append("*")
                            .append(entries[2]);
                }
                file.write(deScris.toString());
                file.flush();
            }catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

}

