package com.example.e5440.sapiadsapp;

import com.google.gson.annotations.SerializedName;

class MarvelCharacters {
    @SerializedName("name")
    private String name;

    public MarvelCharacters(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return name;
    }

    public void setCharacter(String name) {
        this.name = name;
    }
}
