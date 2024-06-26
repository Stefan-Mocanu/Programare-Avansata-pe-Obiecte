package org1.Containers;

/**
 * Clasa care retine obiecte sub forma elementelor din tabelul Song
 */
public class Song {
    int an, id;
    String nume, autor;

    public Song(String an, String id,String nume, String autor) {
        this.an = Integer.parseInt(an);
        this.id = Integer.parseInt(id);
        this.nume = nume;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return autor + " - " + nume + " (" + an + ") [ID: " + id + "]";
    }
}
