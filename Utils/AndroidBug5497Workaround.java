package com.work.diandianzhuan.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * created by vincent on 2020/8/14
 */

public class AndroidBug5497Workaround {

    // For more information, see https://code.google.com/p/android/issues/detail?id=5497
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistActivity(Activity activity) {
        new AndroidBug5497Workaround(activity);
    }

//    private View mChildOfContent;
    private int usableHeightPrevious;
    private ViewGroup.LayoutParams frameLayoutParams;
    private View mContentView;
    Activity mActivity;

    private AndroidBug5497Workaround(Activity activity) {
        this.mActivity = activity;
        mContentView = (FrameLayout) activity.findViewById(android.R.id.content);
//        mContentView = activity.getWindow().getmContentView();
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            if (frameLayoutParams ==null){
                frameLayoutParams = mContentView.getLayoutParams();
            }
            int usableHeightSansKeyboard = mContentView.getHeight();
            int heightDifference = Math.abs(usableHeightSansKeyboard - usableHeightNow);
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightNow;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            mContentView.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mContentView.getWindowVisibleDisplayFrame(r);
        if (StatusBarUtil.isFullScreen(mActivity)){
            return r.bottom;
        }
        return r.bottom - r.top;// 全屏模式下： return r.bottom
    }

}