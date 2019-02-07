package com.pentil.pen_tix.Return.Detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video {
    @SerializedName("results")
    List<DetailVideo> detailVideo;

    public List<DetailVideo> getDetailVideo() {
        return detailVideo;
    }
}
