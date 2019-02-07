package com.pentil.pen_tix.Object;

public class Movie {
    private String title;
    private String genre1;
    private String genre2;
    private String rating;
    private String bahasa;
    private String poster;

    public Movie(String title, String genre1, String genre2, String rating, String bahasa, String poster, String id) {
        this.title = title;
        this.genre1 = genre1;
        this.genre2 = genre2;
        this.rating = rating;
        this.bahasa = bahasa;
        this.poster = poster;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Movie() {
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre1() {
        return genre1;
    }

    public void setGenre1(String genre1) {
        this.genre1 = genre1;
    }

    public String getGenre2() {
        return genre2;
    }

    public void setGenre2(String genre2) {
        this.genre2 = genre2;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
