package com.work.diandianzhuan.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author：created by leaf on 2019-05-07
 * Github地址：https://github.com/Ye-Miao
 * Desc: 状态栏工具类
 */
public class StatusBarUtil {

    private final static Object TAG_STATUS_BAR = new Object();
    private final static Object TAG_ADD_PADDING = new Object();


    /**
     * 全屏状态，透明状态栏
     */
    public static void setFullScreen(@NonNull Activity activity) {
        hideFakeStatusBarView(activity);
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //设置布局能够延伸到状态栏(StatusBar)和导航栏(NavigationBar)里面
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
        }
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }

    public static void setStatusBarDrawable(Activity activity, Drawable drawable) {
        setFullScreen(activity);
        View view = showFakeStatusBarView(activity);
        view.setBackground(drawable);
    }

    public static void setStatusBarDrawable(Activity activity, @DrawableRes int drawable) {
        setStatusBarDrawable(activity, activity.getDrawable(drawable));
    }


    public static void setStatusBarColor(Activity activity, @ColorInt int color) {//客户端自己用，因为需要根据设置的状态栏的背景颜色改变图标的颜色
        if (color == Color.TRANSPARENT) {
            setFullScreen(activity);
            return;
        }
        if (isFullScreen(activity)){
            //全屏回到有状态栏在一些手机上有适配问题，目前还没解决，先用全屏加自定义view的方式处理
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            setStatusBarDrawable(activity, new ColorDrawable(color));
        }else {
            //普通状态
            activity.getWindow().setStatusBarColor(color);
        }
        //设置状态栏字体颜色
        if (ColorUtils.calculateLuminance(color) >= 0.5) {
            setStatusBarTitleColor(activity,true);
        } else {
            setStatusBarTitleColor(activity,false);
        }
    }

    /**
     * 自定义View状态栏
     *
     * @param activity 需要设置的activity
     * @return 状态栏矩形条
     */
    private static View createStatusBarView(Activity activity) {
        // 绘制一个和状态栏一样高的矩形
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.TRANSPARENT);
        //自定义的StatusBarView的Id
        statusBarView.setTag(TAG_STATUS_BAR);
        return statusBarView;
    }

    private static View showFakeStatusBarView(Activity activity) {
        Window window = activity.getWindow();
        View view = getFakeStatusBarView(activity);
        if (view == null) {
            view = createStatusBarView(activity);
            ((ViewGroup) window.getDecorView()).addView(view);
        }
        view.setVisibility(View.VISIBLE);
        setRootPadding(activity);
        return view;
    }

    private static void hideFakeStatusBarView(Activity activity) {
        View view = getFakeStatusBarView(activity);
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        cleanRootPadding(activity);
    }

    private static View getFakeStatusBarView(Activity activity) {
        Window window = activity.getWindow();
        return window.getDecorView().findViewWithTag(TAG_STATUS_BAR);
    }

    /**
     * 设置根布局参数
     */
    private static void setRootPadding(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        int statusBarHeight = getStatusBarHeight(activity);
        if (parent.getTag() == null) {
            parent.setTag(TAG_ADD_PADDING);
            parent.setPadding(parent.getPaddingLeft(), statusBarHeight + parent.getPaddingTop(), parent.getPaddingRight(), parent.getPaddingBottom());
        }
    }

    private static void cleanRootPadding(Activity activity) {
        ViewGroup parent = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        int statusBarHeight = getStatusBarHeight(activity);
        if (parent.getTag() == TAG_ADD_PADDING) {
            parent.setTag(null);
            parent.setPadding(parent.getPaddingLeft(), parent.getPaddingTop() - statusBarHeight, parent.getPaddingRight(), parent.getPaddingBottom());
        }
    }

    public static boolean isFullScreen(Activity activity) {
        Window window = activity.getWindow();
        if (window.getStatusBarColor() == Color.TRANSPARENT
                || (window.getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_FULLSCREEN) == View.SYSTEM_UI_FLAG_FULLSCREEN
                || (window.getDecorView().getSystemUiVisibility() & View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) == View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) {
            return true;
        }
        return false;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public static void setStatusBarTitleColor(Activity activity, boolean isDark) {//isDark表示是否将状态栏的图标和文字修改为暗色
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int vis = activity.getWindow().getDecorView().getSystemUiVisibility();
            if (isDark) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        }
    }
}
