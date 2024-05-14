package org1.Containers;

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
