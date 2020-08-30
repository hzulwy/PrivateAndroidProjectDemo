package com.example.spannablestringdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private TextView tv;
    private SpannableString spannableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);

        spannableString = new SpannableString("世界遗产分为世界文化遗产、" +
                "世界文化景观遗产、世界文化与自然双重遗产、世界自然遗产." +
                "国际文化纪念物与历史场所委员会等非政府组织作为联" +
                "合国教科文组织的协办组织，参与世界遗产的甄选、管理与保护工作.");

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int start = spannableString.toString().indexOf("世界文化遗产");
        int end = start + ("世界文化景观遗产、世界文化与自然双重遗产、世界自然遗产.").toString().length();

        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置文本颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.BLACK),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置背景颜色

        tv.setText(spannableString);
    }
}