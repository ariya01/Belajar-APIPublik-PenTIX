package com.pentil.pen_tix.Return.Detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastObject {
    @SerializedName("cast")
    List<Cast> list;

    public List<Cast> getList() {
        return list;
    }
}
