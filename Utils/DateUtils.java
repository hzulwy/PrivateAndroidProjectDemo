package com.work.diandianzhuan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 开发者：陈飞
 * 时间:2018/7/14 21:50
 * 说明：日期工具类
 */
public class DateUtils {

    /**
     * @属性:yyyy-MM-dd
     * @开发者:陈飞
     * @时间:2018/7/14 21:51
     */
    public static String format_yyyy_MM_dd(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * @属性:yyyy-MM
     * @开发者:陈飞
     * @时间:2018/7/14 21:51
     */
    public static String format_yyyy_MM(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * @属性:yyyy-MM-dd hh:mm
     * @开发者:陈飞
     * @时间:2018/7/14 21:51
     */
    public static String format_yyyy_MM_dd_hh_mm(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        if (date != null) {
            return simpleDateFormat.format(date);
        }
        return null;
    }

    /**
     * @属性:yyyy-MM-dd
     * @开发者:陈飞
     * @时间:2018/7/14 21:51
     */
    public static String format_yyyy_MMstr(String forData) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (forData != null) {
            try {
                Date parse = simpleDateFormat.parse(forData);
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat1.format(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




}
