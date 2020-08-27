package com.work.diandianzhuan.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * 开发者：陈飞
 * 时间:2018/7/29 09:13
 * 说明：
 */
public class APKVersionCodeUtils {
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static long getVersionCode(Context context)
    {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        if (Build.VERSION.SDK_INT>=28){
            return packInfo.getLongVersionCode();
        }
        return packInfo.versionCode;
    }
}
