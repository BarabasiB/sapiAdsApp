package com.example.e5440.sapiadsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetData {

        //Specify the request type and pass the relative URL//

    @GET("v1/public/characters")

        //Wrap the response in a Call object with the type of the expected result//
    Call<MarvelResponse> getAllCharacters(
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("ts") Long ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash
    );

}
