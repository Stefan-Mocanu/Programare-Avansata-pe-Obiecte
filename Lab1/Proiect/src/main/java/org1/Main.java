package org1;

import org1.cont.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static User usr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        usr = new UserAnonim();
        while(true) {
            System.out.println("\nIntrodu o comanda:");
            String com = sc.nextLine();
            String success="1";
            if(Objects.equals(com, "Stop")){
                break;
            }
            String name;
            try{
                name = ((UserAutentificat) usr).getUsername();
            }catch (Exception e){
                name = null;
            }
            try {
                usr.command(com);
            }catch (ComandaInexistenta e){
                System.out.println(e.getMessage());
                success="0";
            }catch (ComandaInterzisa e){
                System.out.println(e.getMessage());
                success="0";
            }catch (Exception e){
                success= "0";
            }
            if(name==null || com.equals("logout"))continue;
            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
                String insertStudent = """
                            insert into audit(user,command,success)
                            values(?,?,?);
                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                    preparedStatement.setString(1,name);
                    preparedStatement.setString(2, com);
                    preparedStatement.setString(3, success);
                    preparedStatement.execute();
                }
            } catch (SQLException e) {

            }
        }
    }
}