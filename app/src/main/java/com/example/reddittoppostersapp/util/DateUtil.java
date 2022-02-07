package com.example.reddittoppostersapp.util;

import android.content.Intent;
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

    public static LocalDateTime getLocalDateTimeFromUtcSec(Long utcSec){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(utcSec), ZoneId.systemDefault());
    }

    public static LocalDateTime getLocalDateTimeFromUtcSec(String utcSec){
        var secInLong = new BigDecimal(utcSec).longValue();
        return getLocalDateTimeFromUtcSec(secInLong);
    }

    public static Duration getElapsedTimeFromDate(String ustSec){
        var current = LocalDateTime.now();
        return Duration.between(getLocalDateTimeFromUtcSec(ustSec), current);
    }

    public static long countElapsedTimeFromDateInHours(String utcSec){
        return getElapsedTimeFromDate(utcSec).toHours();
    }
}
