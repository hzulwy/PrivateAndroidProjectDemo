package com.example.spinnerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    JSONObject jsonObject;
    String myData = "{\"北京市\":[\"北京市\"],\"天津市\":[\"天津市\"],\"河北省\":[\"石家庄市\",\"唐山市\",\"秦皇岛市\",\"邯郸市\"]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            jsonObject = new JSONObject(myData);//将json格式的数据转换为jsonObject对象
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AppCompatSpinner spinner1 = (AppCompatSpinner) findViewById(R.id.spinner1);
        final AppCompatSpinner spinner2 = (AppCompatSpinner) findViewById(R.id.spinner2);

        InitProvinceList(list1);

        spinner1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1));

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //根据所选一级spinner名称加载二级spinner名称
                list2 = InitDistrictList(list1.get(position),list2);
                //将二级spinner数据清空并重新加载数据
                final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, list2);
                spinner2.setAdapter(myAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    //初始化一级spinner数据
    private List<String> InitProvinceList(List<String> list1) {
        Iterator<String> myArray1 = jsonObject.keys();
        while (myArray1.hasNext()){
            list1.add(myArray1.next());
        }
        return list1;
    }

    //初始化二级spinner数据
    private List<String> InitDistrictList(String s, List<String> list2) {
        list2.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(s);
            for(int i=0;i<jsonArray.length();i++){
                list2.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list2;
    }
}