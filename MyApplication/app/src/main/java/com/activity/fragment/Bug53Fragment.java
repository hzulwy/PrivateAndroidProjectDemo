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
import com.FragmentDelegater;
import com.example.myapplication.R;

public class Bug53Fragment extends BaseFragment {

    private String name = "null";

    public static int count = 0;

    public static Fragment newIntance(final String name) {
        Bug53Fragment fragment = new Bug53Fragment();
//        fragment.setFragmentDelegater(new FragmentDelegater(fragment));
        count++;
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Brett","onAttach Bug53Fragment");
        name = getArguments().getString("name");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Brett","onDetach Bug53Fragment");
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

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("Brett", "Bug53Fragment's isVisibleToUser: " + isVisibleToUser);

    }

}
