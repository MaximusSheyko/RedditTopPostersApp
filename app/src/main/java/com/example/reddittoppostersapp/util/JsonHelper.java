package com.example.reddittoppostersapp.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Deprecated
public class JsonHelper {
    @Deprecated
    public static JsonObject convertToJson(String string){
        return new JsonParser().parse(string).getAsJsonObject();
    }
}
