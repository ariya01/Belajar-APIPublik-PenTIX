package com.pentilmis.pen_tix.Return.Genre;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnGenre {
    @SerializedName("genres")
    List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }
}
