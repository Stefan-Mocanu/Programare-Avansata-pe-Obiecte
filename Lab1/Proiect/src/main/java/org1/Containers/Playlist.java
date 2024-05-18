package org1.Containers;

/**
 *
 * Clasa care retine obiecte sub forma elementelor din tabelul Playlist
 */
public class Playlist {
    String nume;
    int id;

    public Playlist(String nume, String id) {
        this.nume = nume;
        this.id = Integer.parseInt(id);
    }

    @Override
    public String toString() {
        return nume + " [ID:"+id+"]";
    }
}
