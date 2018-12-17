package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarvelResponseTest {
    @SerializedName("data")
    @Expose
    private MarvelDataTest data;

    public MarvelDataTest getData() {
        return data;
    }

    public void setData(MarvelDataTest data) {
        this.data = data;
    }
}
