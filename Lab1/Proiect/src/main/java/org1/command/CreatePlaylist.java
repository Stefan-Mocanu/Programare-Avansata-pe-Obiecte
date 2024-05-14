package org1.command;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;
import org1.Main;
import org1.cont.UserAutentificat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreatePlaylist implements Command {
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1){
            throw new NrInvalidArgs("create playlist");
        }
        String user = ((UserAutentificat) Main.usr).getUsername();
        String name = arg[0];
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String insertStudent = """
                            insert into playlist(user,nume)
                            values(?,?);
                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,user);
                preparedStatement.setString(2, name);

                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Deja exista un playlist cu acest nume!");
            throw new RuntimeException();
        }
        System.out.println("Playlist "+arg[0]+" adaugat cu succes");
    }
}
