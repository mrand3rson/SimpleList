package com.example.simplelist.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrei on 07.01.2018.
 */

public class Photo {
    @SerializedName("id")
    int id;

    @SerializedName("webformatURL")
    public String url;
}
