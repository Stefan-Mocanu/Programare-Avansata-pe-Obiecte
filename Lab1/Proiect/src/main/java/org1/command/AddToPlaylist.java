package org1.command;

import org1.Containers.Playlist;
import org1.Main;
import org1.cont.UserAutentificat;

import java.sql.*;
import java.util.Arrays;

public class AddToPlaylist implements Command{
    void addByID(String[] IDs,String id_play){
        boolean verify = true;
        for(String el: IDs){
            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
                String sqlStmt = """
                            select id
                            from song
                            where id = ?
                                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1,el);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(!resultSet.next()){
                            System.out.println("Nu exista melodia cu ID: "+el);
                            verify = false;
                        }
                    }
                }
                sqlStmt = """
                            select id_song
                            from playlist_song
                            where id_song = ?
                            and id_playlist = ?
                                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1,el);
                    preparedStatement.setString(2,id_play);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet.next()){
                            System.out.println("In playlist deja exista melodia cu ID: "+el);
                            verify = false;
                        }
                    }
                }
                if(!verify)throw new RuntimeException();
                sqlStmt= """
                            insert into playlist_song(id_song,id_playlist)
                            values(?,?);
                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1,el);
                    preparedStatement.setString(2, id_play);
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Melodii adaugate cu succes in playlist.");

    }
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length<3 || (!arg[0].equals("byID") && !arg[0].equals("byName"))){
            throw new NrInvalidArgs("add");
        }
        String user = ((UserAutentificat) Main.usr).getUsername();
        if(arg[0].equals("byID")){
            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
                String sqlStmt = """
                        select id
                        from playlist
                        where user = ?
                        and id = ?
                        """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                    preparedStatement.setString(1,user);
                    preparedStatement.setString(2,arg[1]);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(!resultSet.next()){
                            System.out.println("Nu exista acest playlist.");
                            throw new RuntimeException();
                        }
                    }
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            addByID(Arrays.copyOfRange(arg, 2, arg.length),arg[1]);
            return;
        }
        boolean verify = true;

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String sqlStmt = """
                        select id
                        from playlist
                        where nume = ?
                        and user = ?
                        """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                preparedStatement.setString(1,arg[1]);
                preparedStatement.setString(2,user);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(!resultSet.next()){
                        System.out.println("Nu exista acest playlist.");
                        throw new RuntimeException();
                    }else{
                        addByID(Arrays.copyOfRange(arg, 2, arg.length),
                                resultSet.getString("id"));
                    }
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
