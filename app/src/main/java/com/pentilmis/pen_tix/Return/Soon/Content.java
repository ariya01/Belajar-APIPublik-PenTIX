package com.pentilmis.pen_tix.Return.Soon;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Content {
    @SerializedName("id")
    String id_movie;
    @SerializedName("title")
    String title;
    @SerializedName("poster_path")
    String poster;
    @SerializedName("adult")
    String adult;
    @SerializedName("release_date")
    Date release;
    @SerializedName("genre_ids")
    List<String> genre;

    public String getId_movie() {
        return id_movie;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getAdult() {
        return adult;
    }

    public Date getRelease() {
        return release;
    }

    public List<String> getGenre() {
        return genre;
    }
}
