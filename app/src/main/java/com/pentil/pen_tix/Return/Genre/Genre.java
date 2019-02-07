package com.pentil.pen_tix.Return.Genre;

import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
