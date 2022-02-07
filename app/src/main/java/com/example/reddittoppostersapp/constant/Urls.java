package com.example.reddittoppostersapp.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Urls {
    REDDIT("http://www.reddit.com/");

    private final String URL;
}
