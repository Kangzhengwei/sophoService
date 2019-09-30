package com.sopho.utils;

import com.google.gson.Gson;

public class GsonUtils {

    private static volatile Gson gson;

    private GsonUtils() {
    }

    public static Gson getInstance() {
        if (gson == null) {
            synchronized (GsonUtils.class) {
                if (gson == null) {
                    gson = new Gson();
                }
            }
        }
        return gson;
    }


}
