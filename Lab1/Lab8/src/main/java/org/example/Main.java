package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] argv){
        creaza_tabel();
    }
    public static void creaza_tabel(){
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lab_pao", "root", "stefan")) {
            try (Statement statement = connection.createStatement()) {
                String createStudentTable = """
                  CREATE TABLE IF NOT EXISTS smarterCalculatorResults (
                      `id` INT primary key AUTO_INCREMENT,
                      `operation_type` VARCHAR(20) NOT NULL,
                      `left_operand` VARCHAR(254) NOT NULL,
                      `right_operand` VARCHAR(254) NOT NULL,
                      `operation` VARCHAR(2) NOT NULL,
                      `result` VARCHAR(255) NOT NULL
                  );
                """;
                statement.execute(createStudentTable);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
