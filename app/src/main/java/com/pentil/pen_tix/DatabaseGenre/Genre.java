package com.pentil.pen_tix.DatabaseGenre;

public class Genre {
    private int id;
    private String genre;
    private String id_genre;

    public Genre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Genre(int id, String genre, String id_genre) {
        this.id = id;
        this.genre = genre;
        this.id_genre = id_genre;
    }

    public String getId_genre() {
        return id_genre;
    }

    public void setId_genre(String id_genre) {
        this.id_genre = id_genre;
    }
}
