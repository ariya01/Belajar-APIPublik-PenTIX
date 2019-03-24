package com.pentilmis.pen_tix.Return.Detail;

import com.google.gson.annotations.SerializedName;

public class Cast {
    @SerializedName("cast_id")
    String cast_id;

    @SerializedName("character")
    String character;

    @SerializedName("name")
    String name;

    @SerializedName("id")
    String id;

    @SerializedName("profile_path")
    String path;

    public String getCast_id() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }
}
