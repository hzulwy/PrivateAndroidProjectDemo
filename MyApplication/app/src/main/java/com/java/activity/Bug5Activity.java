package com.java.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.activity.fragment.Bug5Fragment;
import com.example.myapplication.R;

/**
 * TODO: Fragment重叠异常
 */
public class Bug5Activity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug5);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout, Bug5Fragment.newIntance(), Bug5Fragment.class.getName());
        transaction.commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Zero", "Bug4Activity requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data.getStringExtra("returnParam"));
    }

}
