package com.example.simplelist.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by Andrei on 07.01.2018.
 */

public class PhotoResponse {
    @SerializedName("hits")
    public ArrayList<Photo> list;
}
