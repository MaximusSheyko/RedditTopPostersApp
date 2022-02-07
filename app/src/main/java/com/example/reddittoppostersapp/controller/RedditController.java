package com.example.reddittoppostersapp.controller;

import com.example.reddittoppostersapp.api.RedditApi;
import com.example.reddittoppostersapp.constant.Urls;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditController {

    public static RedditApi getApi(){
        var gson = new GsonBuilder().setLenient().create();
        var retrofit = new Retrofit.Builder()
                .baseUrl(Urls.REDDIT.getURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit.create(RedditApi.class);
    }
}

