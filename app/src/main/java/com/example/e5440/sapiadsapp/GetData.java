package com.example.e5440.sapiadsapp;

import android.util.Log;

import java.sql.Timestamp;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {

    @GET("v1/public/characters")

    Call<MarvelResponse> getAllCharacters(
            @Query("ts") Long ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );

    @GET("v1/public/characters")

    Call<MarvelResponseTest> getCharacterByName(
            @Query("name") String name,
            @Query("ts") Long ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );



}