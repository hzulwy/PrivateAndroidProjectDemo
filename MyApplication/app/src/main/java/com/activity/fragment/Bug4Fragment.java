package com.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.BaseFragment;
import com.FragmentDelegater;
import com.example.myapplication.R;

/**
 * TODO: Fragment重叠异常
 */
public class Bug4Fragment extends BaseFragment {


    public static Fragment newIntance() {
        Bug4Fragment fragment = new Bug4Fragment();
//        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug4, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .add(R.id.childframeLayout, Bug41Fragment.newIntance(), Bug41Fragment.class.getName())
                        .commitAllowingStateLoss();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Brett", "Bug4Fragment requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data.getStringExtra("returnParam"));
        //在哪个fragment使用startActivityForResult方法，那个fragment的onActivityResult方法就会被回调，其他的fragment的不行
    }

}
