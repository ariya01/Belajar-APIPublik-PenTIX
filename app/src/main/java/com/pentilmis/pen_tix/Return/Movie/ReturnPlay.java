package com.pentilmis.pen_tix.Return.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnPlay {
    @SerializedName("results")
    List<Film> films;

    public List<Film> getFilms() {
        return films;
    }
}
