package com.example.reddittoppostersapp.util;

import android.content.Context;
import android.widget.Toast;

public class Notifier {
    public static void message(String message, Context context){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
