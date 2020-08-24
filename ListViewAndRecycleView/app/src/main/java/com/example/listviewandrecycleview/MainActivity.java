package com.example.listviewandrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private Button btn1,btn2,btn3,btn4;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycleView);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));//这句代码是重中之重
        myRecycleViewAdapter = new MyRecycleViewAdapter(this,getList());
        myRecycleViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(Context context, int position) {
                Toast.makeText(context,list.get(position),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addItemDecoration(new MyItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myRecycleViewAdapter);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    private List getList(){

        for (int i=0;i<30;i++){
            list.add("item"+i);
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn1 ){
            Toast.makeText(this,"线性布局",Toast.LENGTH_LONG).show();
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        }else if(v.getId() == R.id.btn2){
            Toast.makeText(this,"网格布局",Toast.LENGTH_LONG).show();
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else if(v.getId() == R.id.btn3){
            Toast.makeText(this,"瀑布流布局",Toast.LENGTH_LONG).show();
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
        }else{
            myRecycleViewAdapter.addData(3);
        }
    }
}