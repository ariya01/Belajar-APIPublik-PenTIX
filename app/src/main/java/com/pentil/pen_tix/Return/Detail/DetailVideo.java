package com.pentil.pen_tix.Return.Detail;

import com.google.gson.annotations.SerializedName;

public class DetailVideo {
    @SerializedName("key")
    String key;

    @SerializedName("site")
    String site;

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }
}
