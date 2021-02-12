package com.java.activity;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.activity.fragment.Bug111Fragment;
import com.example.myapplication.R;

public class Bug1Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, Bug111Fragment.newIntance(), Bug111Fragment.class.getName());
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Brett","onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Brett","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Brett", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Brett", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Brett", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Brett", "onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("Brett", "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Brett", "onSaveInstanceState");
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e("Brett","onAttachFragment:" + fragment);
    }
}