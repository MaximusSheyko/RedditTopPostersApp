package com.example.reddittoppostersapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Page {
    @SerializedName("before")
    @Expose
    private String previous;

    @SerializedName("after")
    @Expose
    private String next;

    @SerializedName("children")
    @Expose
    private List<Content> postContainers;
}
