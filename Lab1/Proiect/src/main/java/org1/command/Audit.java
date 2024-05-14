package org1.command;

import org1.Containers.AuditCont;
import org1.Containers.Song;
import org1.Paging.Paginare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Audit implements Command {
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1){
            throw new NrInvalidArgs("audit");
        }
        List<AuditCont> deAfisat=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String sqlStmt = """      
                            select id, success, command
                            from audit
                            where user = ?
                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                preparedStatement.setString(1,arg[0]);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        deAfisat.add(new AuditCont(resultSet.getString(3),
                                                    resultSet.getString(2),
                                                    resultSet.getString(1)));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Paginare<AuditCont> pg = new Paginare<>(deAfisat);
        pg.afisare();
    }
}
