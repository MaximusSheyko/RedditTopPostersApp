package com.example.reddittoppostersapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedditApiParams {
    LIMIT_DEFAULT(25),
    POSTS_ON_PAGE_DEFAULT(25);

    private final Integer number;
}
