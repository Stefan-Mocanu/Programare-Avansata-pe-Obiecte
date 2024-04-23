package org.example.l8task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class task4 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lab_pao", "root", "stefan")) {
            String selectSql = """
                    SELECT DISTINCT
                      operation_type,
                      left_operand,
                      right_operand,
                      operation,
                      result
                    FROM smarterCalculatorResults;
                    """;
            PreparedStatement statement = connection.prepareStatement(selectSql);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<CalculatorResult> results = new ArrayList<>();
                while (resultSet.next()) {
                    String type = resultSet.getString("operation_type");
                    String left = resultSet.getString("left_operand");
                    String right = resultSet.getString("right_operand");
                    String op = resultSet.getString("operation");
                    String result = resultSet.getString("result");
                    String delete = """
                            DELETE FROM smarterCalculatorResults
                            WHERE operation_type = ?
                              AND left_operand = ?
                              AND right_operand = ?
                              AND operation = ?
                              AND result = ?
                            ;
                                            """;
                    try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
                        preparedStatement.setString(1, type);
                        preparedStatement.setString(2, left);
                        preparedStatement.setString(3, right);
                        preparedStatement.setString(4, op);
                        preparedStatement.setString(5, result);
                        preparedStatement.execute();
                    }
                    String insertStudent = """
                            insert into smarterCalculatorResults(`operation_type`,`left_operand`,`right_operand`,`operation`,`result`)
                            values(?,?,?,?,?);
                                            """;
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                        preparedStatement.setString(1, type);
                        preparedStatement.setString(2, left);
                        preparedStatement.setString(3, right);
                        preparedStatement.setString(4, op);
                        preparedStatement.setString(5, result);
                        preparedStatement.execute();
                    }


                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

