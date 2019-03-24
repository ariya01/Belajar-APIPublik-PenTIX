package com.pentil.pen_tix.Return.Detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetuenDetail {
    @SerializedName("adult")
    String adult;
    @SerializedName("videos")
    Video video;
    @SerializedName("poster_path")
    String path;
    @SerializedName("title")
    String title;
    @SerializedName("runtime")
    String runtime;
    @SerializedName("genres")
    List<DetailGenre> genres;
    @SerializedName("vote_average")
    String rating;
    @SerializedName("original_language")
    String language;
    @SerializedName("casts")
    CastObject castObject;
    @SerializedName("overview")
    String overview;

    public String getOverview() {
        return overview;
    }

    public CastObject getCastObject() {
        return castObject;
    }

    public String getRating() {
        return rating;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getRuntime() {
        return runtime;
    }

    public List<DetailGenre> getGenres() {
        return genres;
    }

    public String getPath() {
        return path;
    }

    public String getAdult() {
        return adult;
    }

    public Video getVideo() {
        return video;
    }
}
