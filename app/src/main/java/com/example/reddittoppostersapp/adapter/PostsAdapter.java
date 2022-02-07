package com.example.reddittoppostersapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reddittoppostersapp.FullScreenActivity;
import com.example.reddittoppostersapp.R;
import com.example.reddittoppostersapp.constant.DateFormats;
import com.example.reddittoppostersapp.model.Post;
import com.example.reddittoppostersapp.service.ImageService;
import com.example.reddittoppostersapp.util.DateUtil;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private final List<Post> posts;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        var post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.author.setText(post.getAuthor());
        holder.numberComments.setText(String.valueOf(post.getNumberComments()));
        int hours = (int) DateUtil.countElapsedTimeFromDateInHours(post.getDateCreatedInUtcSec());
        holder.timePosted.setText(MessageFormat.format(DateFormats.DATA_POSTED.getFormat(), hours));
        loadImageByUrl(post.getImageUrl(), holder.image);

        holder.image.setOnClickListener(view -> {
            var intent = new Intent(view.getContext(), FullScreenActivity.class);
            intent.putExtra("url", post.getImageUrl());
            view.getContext().startActivity(intent);
        });
    }

    private void loadImageByUrl(String url, ImageView view){
        Glide.with(view.getContext())
                .asBitmap()
                .load(url.trim())
                .into(view);
    }

    @Override
    public int getItemCount() {
        return Objects.isNull(posts) ? 0 : posts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;

        private final TextView title;

        private final TextView author;

        private final TextView numberComments;

        private final TextView timePosted;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.post_author);
            numberComments = itemView.findViewById(R.id.post_num_comments);
            timePosted = itemView.findViewById(R.id.post_time_posted);
            title = itemView.findViewById(R.id.post_head);
            image = itemView.findViewById(R.id.post_image);
        }
    }
}
