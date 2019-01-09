package com.example.e5440.sapiadsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    //base url
    private static final String BASE_URL = "https://gateway.marvel.com/";


    //create a retrofit instance
    public static Retrofit getRetrofitInstance(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    //add the converter

                    .addConverterFactory(GsonConverterFactory.create())

                    //build the retrofit instance

                    .build();
        }
        return retrofit;
    }

}
