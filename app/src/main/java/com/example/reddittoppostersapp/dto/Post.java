package com.example.reddittoppostersapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {
    private String title;
    private String imageUrl;
    private String author;
    private Long dateCreatedUtcSec;
}
