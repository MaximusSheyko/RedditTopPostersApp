package com.example.reddittoppostersapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("thumbnail")
    @Expose
    private String imageUrl;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("num_comments")
    @Expose
    private Integer numberComments;

    @SerializedName("created")
    @Expose
    private String dateCreatedInUtcSec;
}
