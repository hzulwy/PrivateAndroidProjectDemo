package com.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.activity.fragment.Bug4Fragment;
import com.example.myapplication.R;

public class Bug4Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug4);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, Bug4Fragment.newIntance(), Bug4Fragment.class.getName());
        transaction.commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Zero", "Bug4Activity requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data.getStringExtra("returnParam"));
    }
}