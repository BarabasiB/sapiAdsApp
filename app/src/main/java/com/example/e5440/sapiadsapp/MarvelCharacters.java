package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.SerializedName;

class MarvelCharacters {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private String thumbnail;



    /*public MarvelCharacters(String name) {
        this.name = name;
    }*/

    public MarvelCharacters(String name, String description, String thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getCharacter() {
        return name;
    }

    public void setCharacter(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
