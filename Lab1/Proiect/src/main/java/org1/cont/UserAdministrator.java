package org1.cont;

import org1.command.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserAdministrator extends UserAutentificat{
    public UserAdministrator(String u) {
        super(u);
        comenzi = Stream.of(new Object[][] {
                { "logout", new Logout() },
                { "promote", new Promote()},
                { "nrPagini", new ChangePaging()},
                { "create song", new CreateSong()},
                { "create playlist", new CreatePlaylist()},
                { "list", new ListPlaylists()},
                { "add", new AddToPlaylist()},
                { "search", new Search()},
                { "export", new Export()},
                { "import", new Import()},
                { "audit", new Audit()},
                { "run", new RunFromAudit()}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Command) data[1]));
        comenzi_interzise = Stream.of(new Object[][] {
                { "register", true },
                { "login", true},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Boolean) data[1]));
    }

}
