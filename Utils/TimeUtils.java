package com.work.diandianzhuan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String TimestampToDate(String timestamp){
        String result = new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.parseLong(timestamp)*1000L));//将秒转为毫秒
        return result;
    }

    public static String TimestampToDate(long timestamp){
        String result = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp*1000L));//将秒转为毫秒
        return result;
    }
}
