package org1.command;

import java.sql.*;

public class Promote implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1){
            throw new NrInvalidArgs("Promote");
        }
        String username = arg[0];
        Boolean exists = false;
        Boolean admin = false;
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")){
            String selectSql = """
                        SELECT
                          admin
                        FROM utilizator
                        WHERE username = ?
                        """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setString(1,username);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    exists=true;
                    if(rs.getString("admin").equals("1")){
                        admin=true;
                    }
                }
            }
            if(exists){
                if(admin){
                    System.out.println(username + " deja este admin!");
                    return;
                }
                String insertStm = """
                            insert into admin(username)
                            values(?);
                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStm)) {
                    preparedStatement.setString(1,username);
                    preparedStatement.execute();
                }
                String updateStm = """
                            update utilizator
                            set admin = 1
                            where username = ?
                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateStm)) {
                    preparedStatement.setString(1,username);
                    preparedStatement.execute();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
        System.out.println("Utilizator promovat cu succes");
    }
}
