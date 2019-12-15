package com.kkdev.mdbm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TicketInstance {
    private static Retrofit retrofit = null;
    public static String BASE_URL = "https://videospider.in/";

    public static MovieDataService getService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(MovieDataService.class);
    }
}
