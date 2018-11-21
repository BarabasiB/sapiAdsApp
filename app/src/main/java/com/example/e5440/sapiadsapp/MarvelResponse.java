package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MarvelResponse implements Serializable {
    @SerializedName("data")
    @Expose
    private MarvelData data;

    public MarvelData getData() {
        return data;
    }

    public void setData(MarvelData data) {
        this.data = data;
    }
}