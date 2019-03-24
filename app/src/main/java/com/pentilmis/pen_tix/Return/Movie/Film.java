package com.pentilmis.pen_tix.Return.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Film {
    @SerializedName("id")
    String id_movie;

    public String getId_movie() {
        return id_movie;
    }

    @SerializedName("title")
    String title;
    @SerializedName("poster_path")
    String poster;
    @SerializedName("original_language")
    String language;
    @SerializedName("genre_ids")
    List<Integer> genre;

    @SerializedName("vote_average")
    String rating;

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getLanguage() {
        return language;
    }

    public List<Integer> getGenre() {
        return genre;
    }
}
