package com.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

public class Bug41Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug41);

        String param = getIntent().getStringExtra("param");

        TextView show = findViewById(R.id.showparam);
        show.setText(param);

        findViewById(R.id.bntReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("returnParam", "我是从41返回的");
                setResult(100, data);
                finish();
            }
        });

    }
}