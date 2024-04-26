package com.atcard.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimeUtil {
    public static  String getTimeYMD() {
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy/MM/dd");
        return s2.format(new Date());
    }

    public static  String getTimeAll() {
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd&HH:mm:ss");
        return s2.format(new Date());
    }

    public static  String getTime(SimpleDateFormat s) {
        return s.format(new Date());
    }
}
