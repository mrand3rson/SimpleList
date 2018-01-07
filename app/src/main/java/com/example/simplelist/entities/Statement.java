package com.example.simplelist.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Andrei on 07.01.2018.
 */


public class Statement {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String body;
}
