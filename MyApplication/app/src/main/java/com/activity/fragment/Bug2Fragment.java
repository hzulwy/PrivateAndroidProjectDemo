package com.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.BaseFragment;
import com.example.myapplication.R;

public class Bug2Fragment extends BaseFragment {

    private Activity mActivity;
    private Handler mHandler;

    public static Fragment newIntance(Handler handler){
        Bug2Fragment fragment = new Bug2Fragment();
        fragment.setHandler(handler);
        return fragment;
    }

    public void setHandler(Handler handler){
        mHandler = handler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug2,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        while (true){
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }.start();
            }
        });
    }
}
