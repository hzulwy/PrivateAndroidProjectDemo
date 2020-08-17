package com.example.checkedtextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CheckedTextView checkedTextViewJava,checkedTextViewAndroid,checkedTextViewCplus;
    private Button btnInputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkedTextViewAndroid = findViewById(R.id.checktv_android);
        checkedTextViewCplus = findViewById(R.id.checktv_cplus);
        checkedTextViewJava = findViewById(R.id.checktv_java);
        btnInputMessage = findViewById(R.id.btn_inputmessage);

        final List<Integer> list = new ArrayList<>();//技巧，通过id来判断是否使同一个对象
        list.add(checkedTextViewJava.getId());
        list.add(checkedTextViewAndroid.getId());
        list.add(checkedTextViewCplus.getId());

        View.OnClickListener onClickListener = new View.OnClickListener() {//此种方式可以减少代码量，将相识的点击事件逻辑集中在一起
            @Override
            public void onClick(View v) {
                for(int i =0; i<list.size();i++){
                    if(((CheckedTextView)v).getId()==list.get(i).intValue()){
                        ((CheckedTextView)MainActivity.this.findViewById(list.get(i).intValue())).setChecked(true);//坑点:不能使用((CheckedTextView)v).getId()
                    }else{
                        ((CheckedTextView)MainActivity.this.findViewById(list.get(i).intValue())).setChecked(false);
                    }
                }
            }
        };

        checkedTextViewJava.setOnClickListener(onClickListener);
        checkedTextViewAndroid.setOnClickListener(onClickListener);
        checkedTextViewCplus.setOnClickListener(onClickListener);


        btnInputMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<list.size();i++){
                    if( ((CheckedTextView)MainActivity.this.findViewById(list.get(i).intValue())).isChecked()){
                        Toast.makeText(MainActivity.this,((CheckedTextView) MainActivity.this.findViewById(list.get(i))).getText().toString(),Toast.LENGTH_LONG).show();
                        break;
                    }else{

                    }
                }
            }
        });
    }
}