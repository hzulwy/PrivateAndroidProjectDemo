package com.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.BaseFragment;
import com.example.myapplication.R;


public class Bug57Fragment extends BaseFragment {

    private String name = "null";

    public static int count = 0;

    public static Fragment newIntance(final String name) {
        Bug57Fragment fragment = new Bug57Fragment();
        count++;
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        name = getArguments().getString("name");
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        Log.i("Brett", "onInflate");
        FragmentUtils.getFragmentInfo(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.i("Brett", "onCreateView");
//        FragmentUtils.getFragmentInfo(this);
        return inflater.inflate(R.layout.fragment_bug52, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView info = view.findViewById(R.id.fragmentstack);
//        Log.i("Brett", "name: " + name + " count: " + count);
        info.setText(name + " " + count);
    }

}
