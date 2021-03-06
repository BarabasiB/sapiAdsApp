package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MarvelData implements Serializable {

    @SerializedName("results")
    @Expose
    private List<MarvelResults> results;

    public List<MarvelResults> getResults() {
        return results;
    }

    public void setResults(List<MarvelResults> results) {
        this.results = results;
    }
}
