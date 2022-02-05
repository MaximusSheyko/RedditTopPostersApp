package com.example.reddittoppostersapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reddittoppostersapp.adapter.PostsAdapter;
import com.example.reddittoppostersapp.api.RedditApi;
import com.example.reddittoppostersapp.controller.RedditController;
import com.example.reddittoppostersapp.model.Content;
import com.example.reddittoppostersapp.model.Listing;
import com.example.reddittoppostersapp.model.Post;
import com.example.reddittoppostersapp.service.ImageService;
import com.example.reddittoppostersapp.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static RedditApi redditApi;

    private RecyclerView postsRecycler;

    private List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redditApi = RedditController.getApi();

        List<Post> posts = new ArrayList<>();
        postsRecycler = findViewById(R.id.post_list);
        var adapter = new PostsAdapter(this, posts, new DateUtil(), new ImageService(this));
        postsRecycler.setAdapter(adapter);

        redditApi.postsTop(25, 25, null, null)
                .enqueue(new Callback<Listing>() {
                    @Override
                    public void onResponse(Call<Listing> call, Response<Listing> response) {
                        var temp = response.body().getPage().getPostContainers()
                                .stream().map(Content::getPost).collect(Collectors.toList());
                        posts.addAll(temp);
                        postsRecycler.getAdapter().notifyDataSetChanged();
                        System.out.println(posts);
                    }

                    @Override
                    public void onFailure(Call<Listing> call, Throwable t) {

                    }
                });
    }
}