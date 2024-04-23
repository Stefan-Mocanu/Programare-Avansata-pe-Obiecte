package org.example.l8task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class task2 {
    public static void main(String[] args) {
        for (CalculatorResult result : t2()) {
            CalculatorRequest request = result.getRequest();
            System.out.println("Operation " + request + " has result " + result.computeResult());
        }
    }
    public static List<CalculatorResult> t2(){
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lab_pao", "root", "stefan")) {
            String selectSql = """
                        SELECT
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
                    String type,op;
                    Object left=null, right=null, result=null;
                    type = resultSet.getString("operation_type");
                    op = resultSet.getString("operation");
                    switch (type){
                        case "Integer":
                            left = Integer.valueOf(resultSet.getString("left_operand"));
                            right = Integer.valueOf(resultSet.getString("right_operand"));
                            result = Integer.valueOf(resultSet.getString("result"));
                            CalculatorRequest aux = new CalculatorRequest(left,right,op);
                            results.add(new IntegerCalculatorResult(aux));
                            break;
                        case "Double":
                            left = Double.valueOf(resultSet.getString("left_operand"));
                            right = Double.valueOf(resultSet.getString("right_operand"));
                            result = Double.valueOf(resultSet.getString("result"));
                            CalculatorRequest aux1 = new CalculatorRequest(left,right,op);
                            results.add(new DoubleCalculatorResult(aux1));
                            break;
                        case "Boolean":
                            left = Boolean.valueOf(resultSet.getString("left_operand"));
                            right = Boolean.valueOf(resultSet.getString("right_operand"));
                            result = Boolean.valueOf(resultSet.getString("result"));
                            CalculatorRequest aux2 = new CalculatorRequest(left,right,op);
                            results.add(new BooleanCalculatorResult(aux2));
                            break;
                    }

                }
                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
