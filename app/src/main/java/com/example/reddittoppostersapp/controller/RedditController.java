package com.example.reddittoppostersapp.controller;

import com.example.reddittoppostersapp.api.RedditApi;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RedditController {

    public static RedditApi getApi(){
        var gson = new GsonBuilder().setLenient().create();
        var retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.REDDIT.getURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit.create(RedditApi.class);
    }
}

