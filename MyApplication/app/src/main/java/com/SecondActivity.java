package com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.activity.fragment.MainFragment;
import com.example.myapplication.R;

public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(savedInstanceState == null){
            //TODO: 1.获取fragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();
            //TODO: 2.开启一个fragment事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //TODO: 3.向FrameLayout容器添加MainFragment,现在并未真正执行
            transaction.add(R.id.frameLayout, MainFragment.newIntance(), MainFragment.class.getName());


            //TODO: 4.提交事务，真正去执行添加动作
            transaction.commit();
        }
    }
}