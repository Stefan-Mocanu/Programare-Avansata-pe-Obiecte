package org1.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateSong implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=3){
            throw new NrInvalidArgs("create song");
        }
        String name = arg[0];
        String author = arg[1];
        String an = arg[2];
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String insertStudent = """
                            insert into song(name,author,an)
                            values(?,?,?);
                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,name);
                preparedStatement.setString(2, author);
                preparedStatement.setString(3, an);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Deja exista un cantec cu acest nume si artist!");
            throw new RuntimeException();
        }
        System.out.println(name+" de "+author+" a fost aduagat cu succes");
    }
}
