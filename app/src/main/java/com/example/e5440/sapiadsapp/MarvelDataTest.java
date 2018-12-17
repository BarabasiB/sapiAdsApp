package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarvelDataTest {
    @SerializedName("results")
    @Expose
    private MarvelResults results;

    public MarvelResults getResults() {
        return results;
    }

    public void setResults(MarvelResults results) {
        this.results = results;
    }
}
