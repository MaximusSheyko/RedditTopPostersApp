package com.example.reddittoppostersapp.util;

import android.icu.number.NumberFormatter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    public LocalDateTime getLocalDateTimeFromUtcSec(Long utcSec){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(utcSec), ZoneId.systemDefault());
    }

    public LocalDateTime getLocalDateTimeFromUtcSec(String utcSec){
        var secInLong = new BigDecimal(utcSec).longValue();
        return getLocalDateTimeFromUtcSec(secInLong);
    }

    public Duration getElapsedTimeFromDate(String ustSec){
        var current = LocalDateTime.now();
        return Duration.between(getLocalDateTimeFromUtcSec(ustSec), current);
    }
}
