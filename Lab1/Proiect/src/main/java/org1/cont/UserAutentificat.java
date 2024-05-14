package org1.cont;

import org1.command.*;


import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserAutentificat extends User{
    String username;

    public String getUsername() {
        return username;
    }

    public UserAutentificat(String u){
        super();
        username = u;
        System.out.println("Bun venit "+username+"!");
        comenzi = Stream.of(new Object[][] {
                { "logout", new Logout() },
                { "nrPagini", new ChangePaging()},
                { "create playlist", new CreatePlaylist()},
                { "list", new ListPlaylists()},
                { "add", new AddToPlaylist()},
                { "search", new Search()},
                { "export", new Export()},
                { "import", new Import()},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Command) data[1]));
        comenzi_interzise = Stream.of(new Object[][] {
                { "promote", true},
                { "register", true },
                { "login", true},
                { "create song", true},
                { "audit", true},
                { "run", true}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Boolean) data[1]));
    }
}
