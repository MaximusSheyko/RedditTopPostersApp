package com.example.reddittoppostersapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DateFormats {
    DATA_POSTED("{0} hours ago");
    private final String format;
}
