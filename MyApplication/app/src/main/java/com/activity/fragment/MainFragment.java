package com.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.java.activity.Bug1Activity;
import com.java.activity.Bug2Activity;
import com.java.activity.Bug3Activity;
import com.java.activity.Bug4Activity;
import com.java.activity.Bug5Activity;
import com.java.activity.ViewpagerActivity;

public class MainFragment extends ListFragment {

    public static Fragment newIntance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    ArrayAdapter<String>arrayAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] array = new String[]{
                "getActivity==null",
                "Can not perform this action after onSaveInstanceState",
                "Fragment重叠异常",
                "嵌套的fragment不能在onActivityResult()中接收到返回值,>23版本的可以",
                "未必靠谱的出栈方法remove()",
                "mAvailIndeices的BUG",
                "popBackStack的坑",
                "pop多个Fragment时转场动画 带来的问题",
                "进入新的Fragment并立刻关闭当前Fragment 时的一些问题",
                "Fragment+viewpager",
                "静态加载"
        };
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, array);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = arrayAdapter.getItem(position);
        Toast.makeText(getActivity(),item,Toast.LENGTH_SHORT).show();

        switch (position){
            case 0:
                Intent intent0 = new Intent(getActivity(), Bug1Activity.class);
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(getActivity(), Bug2Activity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(getActivity(), Bug3Activity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(getActivity(), Bug4Activity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(getActivity(), Bug5Activity.class);
                startActivity(intent4);
                break;
            case 9:
                Intent intent9 = new Intent(getActivity(), ViewpagerActivity.class);
                startActivity(intent9);
                break;
            default:
                break;
        }

    }
}
