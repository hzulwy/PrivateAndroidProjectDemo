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

import com.BaseFragment;
import com.example.myapplication.R;
import com.java.activity.Bug41Activity;


public class Bug41Fragment extends BaseFragment {


    public static Fragment newIntance() {
        Bug41Fragment fragment = new Bug41Fragment();
//        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bug41, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBug41 = new Intent(getActivity(), Bug41Activity.class);
                goBug41.putExtra("param","这是我从Bug41Fragment带过来的参数");
                startActivityForResult(goBug41, 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Brett","Bug41Fragment requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data.getStringExtra("returnParam"));
    }
}
