package com.activity.fragment;

import android.util.Log;

import androidx.fragment.app.Fragment;

public class FragmentUtils {

    public static void getFragmentInfo(Fragment fragment) {
        if (fragment == null) {
            Log.i("Zero", "fragment is null");
            return;
        }
        Log.i("Zero", "getId: " + fragment.getId()
                + " isAdded: " + fragment.isAdded()
                + " isDetached: " + fragment.isDetached()
                + " isHidden: " + fragment.isHidden()
                + " isInLayout: " + fragment.isInLayout()
                + " isRemoving: " + fragment.isRemoving()
                + " isResumed: " + fragment.isResumed()
                + " isVisible: " + fragment.isVisible()
                + " getTag: " + fragment.getTag()
        );
    }
}
