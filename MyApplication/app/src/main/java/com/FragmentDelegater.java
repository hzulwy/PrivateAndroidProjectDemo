package com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDelegater extends Fragment {

    Fragment mFragment;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dumpLifeCycle("onAttach: "+mFragment.hashCode());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dumpLifeCycle("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dumpLifeCycle("onCreateView");
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dumpLifeCycle("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dumpLifeCycle("onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        dumpLifeCycle("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        dumpLifeCycle("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        dumpLifeCycle("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        dumpLifeCycle("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        dumpLifeCycle("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dumpLifeCycle("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dumpLifeCycle("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dumpLifeCycle("onDetach");
    }

    public FragmentDelegater(Fragment mFragment) {
        super();
        this.mFragment = mFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i("Brett", "setUserVisibleHint isVisibleToUser: " + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i("Brett", "onHiddenChanged hidden: "+hidden);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i("Brett", "requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);

    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        dumpLifeCycle("onInflate");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        dumpLifeCycle("onSaveInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dumpLifeCycle("onConfigurationChanged");
    }

    private void dumpLifeCycle(final String method) {
        Log.i("Brett", "name: " + mFragment.getClass().getSimpleName() + " -> " + method);
    }
}
