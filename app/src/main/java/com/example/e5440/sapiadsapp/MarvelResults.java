package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MarvelResults implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnail")
    @Expose
    private MarvelThumbnail thumbnail;

    /*
    private List<MarvelResults> results;

    public List<MarvelResults> getResults() {
        return results;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail.getFullPath();
    }

    public void setThumbnail(MarvelThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
