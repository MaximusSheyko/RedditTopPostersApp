package com.example.reddittoppostersapp.api;

import com.example.reddittoppostersapp.model.Listing;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("/top.json")
    @NonNull
    Call<Listing> postsTop(@Query("limit") int numberPost,
                           @Query("count") int numberPostOnPage,
                           @Query("after") String nextPageId,
                           @Query("before") String prevPageId);
}
