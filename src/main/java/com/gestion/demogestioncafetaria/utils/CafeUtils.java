package com.gestion.demogestioncafetaria.utils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CafeUtils {
    private CafeUtils() {}

    public static String getUUid() {
        Date date = new Date();
        long l = date.getTime();
        return "BILL-" + l;
    }

    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
        return new JSONArray(data);
    }

    public static Map<String, String> getMapFromJson(String data) {
        if (!Strings.isNullOrEmpty(data)) {
            return new Gson().fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
        }
        return new HashMap<>();
    }
}
