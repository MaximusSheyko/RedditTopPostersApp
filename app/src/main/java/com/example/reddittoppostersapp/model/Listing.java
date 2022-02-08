package com.example.reddittoppostersapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.util.EnumSet;

import lombok.Data;

@Data
public class Listing {
    @SerializedName("data")
    @Expose
    private Page page;
}
