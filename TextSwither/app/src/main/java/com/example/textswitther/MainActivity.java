package com.example.textswitther;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher;
    private Button btnStartCarousel,btnstopCarousel;
    private String[] items = new String[] { "新春特别活动，楚舆狂歌套限时出售！", "三周年红发效果图放出！", "冬至趣味活动开启，一起来吃冬至宴席。" };
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btnstopCarousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeMessages(i);
            }
        });
        btnStartCarousel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = mHandler.obtainMessage(1);
                msg.what = i;
                mHandler.sendMessage(msg);
            }
        });
    }

    private void initView() {
        btnStartCarousel = findViewById(R.id.btn_startCarousel);
        btnstopCarousel = findViewById(R.id.btn_stopCarousel);
        textSwitcher = findViewById(R.id.tvSwitcher);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                return textView;
            }
        });
        textSwitcher.setInAnimation(getApplicationContext(), R.anim.slide_in_left);
        textSwitcher.setOutAnimation(getApplicationContext(), R.anim.slide_out_right);

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textSwitcher.setText(items[i % items.length]);
            i++;
            Message msgg = mHandler.obtainMessage(1);
            msgg.what = i;
            mHandler.sendMessageDelayed(msgg, 1000);
        }
    };

}