package com.romanvytv.webapiandroidtest.utils;

/**
 * Created by Роман on 29.08.2017.
 */

public class DateUtil {

    public static String parseDate(String sqlDate){
        StringBuilder builder = new StringBuilder();
        builder.append(sqlDate.substring(8,10));
        builder.append(".");
        builder.append(sqlDate.substring(5,7));
        builder.append(".");
        builder.append(sqlDate.substring(0,4));
        return builder.toString();
    }

    public static String parseTimeForChat(String sqlDate){
        StringBuilder builder = new StringBuilder();
        builder.append(sqlDate.substring(11,16));
        return builder.toString();
    }
}
