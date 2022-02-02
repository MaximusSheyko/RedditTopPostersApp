package com.example.reddittoppostersapp.util;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDateTime convertUtcSecToLocalDate(long utcSec){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(utcSec), ZoneId.systemDefault());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Duration getDurationBetweenDatesCurrentAndTarget(LocalDateTime target){
        var current = LocalDateTime.now();
        return Duration.between(target, current);
    }
}
