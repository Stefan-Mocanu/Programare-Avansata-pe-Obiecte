package org1.cont;

import org1.command.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAnonim extends User{
    public UserAnonim(){
        comenzi =Stream.of(new Object[][] {
                { "register", new Register() },
                { "login", new Login()},
                { "nrPagini", new ChangePaging()},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Command) data[1]));
        comenzi_interzise = Stream.of(new Object[][] {
                { "logout", true },
                { "promote", true},
                { "create song", true},
                { "create playlist", true},
                { "list", true},
                { "add", true},
                { "search", true},
                { "export", true},
                { "import", true},
                { "audit", true},
                { "run", true}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Boolean) data[1]));
    }
}
