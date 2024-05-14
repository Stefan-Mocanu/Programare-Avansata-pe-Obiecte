package org1.command;

import org1.Main;
import org1.cont.UserAdministrator;
import org1.cont.UserAutentificat;

import java.sql.*;

public class Login implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=2){
            throw new NrInvalidArgs("Login");
        }
        String username = arg[0];
        String pass = arg[1];
        Boolean exists = false;
        Boolean admin = false;
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")){
            String selectSql = """
                        SELECT
                          admin
                        FROM utilizator
                        WHERE username = ? and pass = ?
                        """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
                preparedStatement.setString(1,username);
                preparedStatement.setString(2, pass);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()){
                    exists=true;
                    if(rs.getString("admin").equals("1")){
                        admin=true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        if(exists){
            if(admin){
                Main.usr = new UserAdministrator(username);
            }else{
                Main.usr = new UserAutentificat(username);
            }
        }
    }
}
