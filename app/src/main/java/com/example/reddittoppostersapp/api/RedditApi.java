package com.example.reddittoppostersapp.api;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("/top.json")
    @NonNull
    Call<String> postsTop(@Query("limit") int numberPost,
                              @Query("count") int numberPostOnPage,
                              @Query("after") String nextPageId,
                              @Query("before") String prevPageId);
}
