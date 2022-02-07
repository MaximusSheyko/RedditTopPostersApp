package com.example.reddittoppostersapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Listing {
    @SerializedName("data")
    @Expose
    private Page page;
}
