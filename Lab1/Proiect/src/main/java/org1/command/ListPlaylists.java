package org1.command;


import org1.Containers.Playlist;
import org1.Main;
import org1.Paging.Paginare;
import org1.cont.UserAutentificat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ListPlaylists implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1 || !arg[0].equals( "playlists")){
            throw new NrInvalidArgs("list playlists");
        }
        String nume = ((UserAutentificat) Main.usr).getUsername();
        List<Playlist> playlistList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String insertStudent = """
                            select id, nume
                            from playlist
                            where user = ?
                                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,nume);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        playlistList.add(new Playlist(resultSet.getString("nume"),
                                                      resultSet.getString("id")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Paginare<Playlist> pg = new Paginare<>(playlistList);
        pg.afisare();
    }
}
