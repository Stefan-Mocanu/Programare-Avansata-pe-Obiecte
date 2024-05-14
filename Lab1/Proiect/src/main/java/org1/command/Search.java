package org1.command;

import org1.Containers.Playlist;
import org1.Containers.Song;
import org1.Paging.Paginare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Search implements Command{
    List<Song> name(String name){
        List<Song> s = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String insertStudent = """
                            select id, name, author, an
                            from song
                            where name like CONCAT(?, '%')
                                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        s.add(new Song(resultSet.getString("an"),
                                        resultSet.getString("id"),
                                        resultSet.getString("name"),
                                        resultSet.getString("author")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
    List<Song> autor(String autor){
        List<Song> s = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String sqlStmt = """
                            select id, name, author, an
                            from song
                            where author like CONCAT(?, '%')
                                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                preparedStatement.setString(1,autor);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        s.add(new Song(resultSet.getString("an"),
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getString("author")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=2 || (!arg[0].equals("name") && !arg[0].equals("author"))){
            throw new NrInvalidArgs("search");
        }
        List<Song> songs;
        if(arg[0].equals("name")){
            songs = name(arg[1]);
        }else{
            songs = autor(arg[1]);
        }
        if(songs.isEmpty()){
            System.out.println("Nu exista niciun rezultat.");
            return;
        }
        Paginare<Song> pg = new Paginare<>(songs);
        pg.afisare();
    }
}
