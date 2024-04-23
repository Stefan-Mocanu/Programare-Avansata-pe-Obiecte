package org.example.l8task1;

import java.sql.*;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ce doriti sa stergeti?\n (1) Integer \n (2) Boolean \n (3) Double");
        int x = sc.nextInt();
        String tip = "";
        if (x==1)tip = "Integer";
        if (x==2)tip = "Boolean";
        if (x==3)tip = "Double";
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lab_pao", "root", "stefan")) {
            String insertStudent = """  
                                DELETE FROM smarterCalculatorResults
                                WHERE operation_type = ?;
                                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,tip);
                preparedStatement.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
