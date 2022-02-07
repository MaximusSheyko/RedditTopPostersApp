package com.example.reddittoppostersapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reddittoppostersapp.adapter.PostsAdapter;
import com.example.reddittoppostersapp.api.RedditApi;
import com.example.reddittoppostersapp.constant.RedditApiParams;
import com.example.reddittoppostersapp.controller.RedditController;
import com.example.reddittoppostersapp.model.Content;
import com.example.reddittoppostersapp.model.Listing;
import com.example.reddittoppostersapp.model.Post;
import com.example.reddittoppostersapp.util.Notifier;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static RedditApi redditApi;

    private RecyclerView postsRecycler;

    private ArrayList<Post> posts;

    private String nextPageId;

    private static final String SAVE_POSTS_KEY = "CONTENT_POSTS";

    private static final String SAVE_PAGE_KEY = "CONTENT_PAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redditApi = RedditController.getApi();
        posts = new ArrayList<>();
        postsRecycler = findViewById(R.id.post_list);
        var adapter = new PostsAdapter(posts);
        postsRecycler.setAdapter(adapter);
        FloatingActionButton nextPage = findViewById(R.id.post_load_new_posts);

        firstStart(nextPage);

        nextPage.setOnClickListener(view -> {
            Notifier.message("Download...", MainActivity.this);
            redditApi.postsTop(RedditApiParams.LIMIT_DEFAULT.getNumber(), RedditApiParams.POSTS_ON_PAGE_DEFAULT.getNumber(),
                    nextPageId).enqueue(new Callback<Listing>() {
                @Override
                public void onResponse(Call<Listing> call, Response<Listing> response) {
                    var listing = response.body();
                    if (Objects.nonNull(listing)){
                        //posts.clear(); -> if you want get only next page
                        updateContent(listing);
                        Notifier.message("Posts added", MainActivity.this);
                    }
                }

                @Override
                public void onFailure(Call<Listing> call, Throwable t) {
                    Notifier.message("Failed connection", MainActivity.this);
                }
            });
        });
    }

    private void firstStart(FloatingActionButton nextPage){
        Button start = findViewById(R.id.main_start);
        nextPage.setVisibility(View.INVISIBLE);
        start.setOnClickListener(view -> {
            nextPage.setVisibility(View.VISIBLE);
            start.setVisibility(View.INVISIBLE);
            nextPage.callOnClick();
        });
    }

    private void updateContent(Listing listing){
        nextPageId = listing.getPage().getNext();
        posts.addAll(getPosts(listing));
        postsRecycler.getAdapter().notifyDataSetChanged();
    }


    private List<Post> getPosts(Listing listing){
        return listing.getPage().getPostContainers().stream()
                .map(Content::getPost).collect(Collectors.toList());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVE_POSTS_KEY, posts);
        outState.putString(SAVE_PAGE_KEY, nextPageId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        nextPageId = inState.getString(SAVE_PAGE_KEY);
        posts.addAll(inState.getParcelableArrayList(SAVE_POSTS_KEY));
    }
}