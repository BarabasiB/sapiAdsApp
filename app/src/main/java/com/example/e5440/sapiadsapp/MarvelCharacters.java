package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.SerializedName;

class MarvelCharacters {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;


    /*public MarvelCharacters(String name) {
        this.name = name;
    }*/

    public MarvelCharacters(String name, String description) {
        this.name = name;
        this.description = description;
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
}
