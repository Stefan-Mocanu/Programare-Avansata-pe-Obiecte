package org.example.l8task1;


import java.sql.*;
import java.util.List;

public class task1 {
    public static void main(String[] argv){
        creaza_tabel();
        String[] input = {"1", "+", "2",
                "2", "*", "5",
                "1", "+", "5.0",
                "1.0", "-", "2",
                "10.0", "/", "1",
                "true", "||", "false"};
        List<CalculatorResult> calculationResults =  SmarterCalculator.calculate(input);

        for (CalculatorResult result : calculationResults) {
            CalculatorRequest request = result.getRequest();

            //System.out.println("Operation " + request + " has result " + result.computeResult());
            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lab_pao", "root", "stefan")) {
                String insertStudent = """
                            insert into smarterCalculatorResults(`operation_type`,`left_operand`,`right_operand`,`operation`,`result`)
                            values(?,?,?,?,?);
                                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                    preparedStatement.setString(1,request.getRequestType());
                    preparedStatement.setString(2, request.leftOperand().toString());
                    preparedStatement.setString(3, request.rightOperand().toString());
                    preparedStatement.setString(4,request.operation());
                    preparedStatement.setString(5, result.computeResult().toString());
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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
