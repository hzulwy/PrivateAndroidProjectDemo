package com.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;

import com.activity.fragment.Bug31Fragment;
import com.activity.fragment.Bug3Fragment;
import com.example.myapplication.R;

    public class Bug3Activity extends FragmentActivity {

        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frameLayout, Bug3Fragment.newIntance(mHandler), Bug3Fragment.class.getName());
            transaction.add(R.id.frameLayout, Bug31Fragment.newIntance(mHandler), Bug31Fragment.class.getName());
                transaction.commit();
            }
        };

        private Handler handler = new Handler(msg -> {
            Log.i("Brett","sdf");
            return false;
        });

        private Runnable runnable = ()->{};

        @Override
        public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
            super.onSaveInstanceState(outState, outPersistentState);
        }

        @Override
        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bug3);

            //解决方法：对savedInstanceState坐下判空处理
            if(savedInstanceState == null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frameLayout, Bug3Fragment.newIntance(mHandler), Bug3Fragment.class.getName());
                transaction.commit();

                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.frameLayout, Bug31Fragment.newIntance(mHandler), Bug31Fragment.class.getName());
                transaction.commit();
            }


        }
}