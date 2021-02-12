package com.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.BaseFragment;
import com.arch.FragmentObserver;
import com.example.myapplication.R;

public class Bug111Fragment extends BaseFragment {

    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Toast.makeText(getActivity(),"任务执行了",Toast.LENGTH_LONG).show();
        }
    };

    class NetWorkTask implements Runnable, LifecycleObserver{
        boolean isCancel = false;

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDetach(){
            Log.i("Brett", "观察到fragment生命周期走到onDetach了，任务取消。。。");
        }

        FragmentObserver fragmentObserver = new FragmentObserver() {
            @Override
            public void onAttach(Context context) {

            }

            @Override
            public void onDetach() {
                Log.i("Brett", "观察到fragment生命周期走到onDetach了，任务取消。。。");
                isCancel = true;
                mhandler.removeCallbacksAndMessages(null);
            }
        };


        @Override
        public void run() {
            try {
                Thread.sleep(8500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isCancel){
                return;
            }
            mhandler.sendEmptyMessage(1);
        }
    }

    //TODO: [解决方案]在onAttach(Activity activity)里赋值，使用mActivity代替getActivity()，
    // 保证Fragment即使在onDetach后，仍持有Activity的引用（
    // 有引起内存泄露的风险，但是异步任务没停止的情况下，本身就可能已内存泄漏，相比Crash，这种做法“安全”些）
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Zero", "onAttach : " + getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Zero", "onCreate : " + getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Brett", "onCreateView : " + getActivity());
        return inflater.inflate(R.layout.fragment_bug1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Brett", "onViewCreated : " + getActivity());
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetWorkTask netWorkTask = new NetWorkTask();
                getLifecycle().addObserver(netWorkTask);
                Thread thread = new Thread(netWorkTask);
                thread.start();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Brett", "onActivityCreated : " + getActivity());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("Brett", "onViewStateRestored : " + getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Brett", "onStart : " + getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Brett", "onResume : " + getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Brett", "onPause : " + getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Brett", "onStop : " + getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Brett", "onDestroyView : " + getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Brett", "onDestroy : " + getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Brett", "onDetach : " + getActivity());
    }

    public static Fragment newIntance() {
        Bug111Fragment fragment = new Bug111Fragment();
        return fragment;
    }
}
