package org1.command;

import org1.Containers.AuditCont;
import org1.cont.User;
import org1.cont.UserAdministrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunFromAudit implements Command{
    @Override
    public void UseCommand(String[] arg) {
        if(arg.length!=1){
            throw new NrInvalidArgs("run");
        }
        String comm,nume;
        List<AuditCont> deAfisat=new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/audiolib", "root", "stefan")) {
            String sqlStmt = """      
                            select command,user
                            from audit
                            where id =?
                            """;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStmt)) {
                preparedStatement.setString(1,arg[0]);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if(resultSet.next()){
                        comm = resultSet.getString(1);
                        nume = resultSet.getString(2);
                    }else{
                        throw new RuntimeException();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            User usr = new UserAdministrator(nume);
            usr.command(comm);
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
