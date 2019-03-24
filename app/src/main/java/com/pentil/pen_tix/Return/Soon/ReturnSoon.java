package com.pentil.pen_tix.Return.Soon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReturnSoon {
    @SerializedName("results")
    List<Content> contents;

    public List<Content> getContents() {
        return contents;
    }
}
