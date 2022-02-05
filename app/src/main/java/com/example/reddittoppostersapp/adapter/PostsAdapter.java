package com.example.reddittoppostersapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.reddittoppostersapp.R;
import com.example.reddittoppostersapp.model.Post;
import com.example.reddittoppostersapp.service.ImageService;
import com.example.reddittoppostersapp.util.DateUtil;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    public Context context;

    private final List<Post> posts;

    private final DateUtil dateUtil;

    private final ImageService imageService;

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent,false);
        return new PostsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        var post = posts.get(position);
        Log.i("post", post.toString());
        holder.title.setText(post.getTitle());
        holder.author.setText(post.getAuthor());
        holder.numberComments.setText(post.getNumberComments().toString());
        int hours = (int) dateUtil.getElapsedTimeFromDate(post
                .getDateCreatedInUtcSec())
                .toHours();
        holder.timePosted.setText(MessageFormat.format(DataFormats.DATA_POSTED.getFormat(), hours));
        imageService.loadImageByUrl(post.getImageUrl(), holder.image);
    }

    @Override
    public int getItemCount() {
        return Objects.isNull(posts) ? 0 : posts.size();
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        private final TextView title;

        private final TextView author;

        private TextView numberComments;

        private TextView timePosted;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.post_author);
            numberComments = itemView.findViewById(R.id.post_num_comments);
            timePosted = itemView.findViewById(R.id.post_time_posted);
            title = itemView.findViewById(R.id.post_head);
            image = itemView.findViewById(R.id.post_image);
        }
    }
}
