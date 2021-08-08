package com.brett.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @author dell
 */
public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn_id)
     Button btn;
    @InjectView(R.id.btn_id2)
     Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.injectView(this);
        Utils.injectEvent(this);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MainActivity","btn_id2 is clicked");
            }
        });
    }


    @onClick({R.id.btn_id})
    public void BtnClick(View view){
        switch (view.getId()){
            case R.id.btn_id:
                Log.e("MainActivity","btn_id is clicked");
        }
    }
}