package com.romanvytv.webapiandroidtest.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

/**
 * Created by Роман on 14.08.2017.
 */

public class TokenUtils {


    private static final String TOKEN_KEY = "TOKEN";

    public TokenUtils() {
    }

    public static void saveToken(@NonNull String token) {
        Hawk.put(TOKEN_KEY, token);
    }

    @NonNull
    public static String getToken() {
        return Hawk.get(TOKEN_KEY, "");
    }
}
