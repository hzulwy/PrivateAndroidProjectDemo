package com.brett.hotfixdemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.brett.hotfixdemo.utils.Hotfix;

import java.io.File;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //执行热修复。 插入补丁dex
        // /data/data/xxx/files/xxxx.dex
        // /sdcard/xxx.dex
        //需要开启存储权限，注意Android10以上的分区存储
//        Log.e("MyApplication",base.getExternalFilesDir("apk").getAbsolutePath());
        Hotfix.installPatch(this,new File(base.getExternalFilesDir("apk").getAbsolutePath()+"/classes.dex"));
    }
}
