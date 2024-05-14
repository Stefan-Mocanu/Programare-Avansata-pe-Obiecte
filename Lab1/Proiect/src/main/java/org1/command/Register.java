package org1.command;

import java.sql.*;



public class Register implements Command{
    @Override
    public void UseCommand(String[] arg){
        if(arg.length!=2){
            throw new NrInvalidArgs("register");
        }
        String username = arg[0];
        String pass = arg[1];
        String admin = "0";
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")){
            String selectSql = """
                        SELECT
                          count(*) a
                        FROM admin
                        """;
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectSql)) {
                    if (resultSet.next()) {
                        if(resultSet.getString("a").equals("0")){
                            admin="1";
                        }
                    }
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return;
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String insertStudent = """
                            insert into utilizator(username,pass,admin)
                            values(?,?,?);
                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                preparedStatement.setString(1,username);
                preparedStatement.setString(2, pass);
                preparedStatement.setString(3, admin);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Deja exista un user cu acest username! Incearca din nou!");
            throw new RuntimeException();
        }
        if(admin.equals("1")){
            try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
                String insertStudent = """
                            insert into admin(username)
                            values(?);
                            """;
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertStudent)) {
                    preparedStatement.setString(1,username);
                    preparedStatement.execute();
                }
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
        System.out.println("Utilizator adaugat cu succes!");
        new Login().UseCommand(arg);
    }
}
