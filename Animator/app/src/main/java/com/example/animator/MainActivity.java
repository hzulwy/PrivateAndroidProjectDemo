package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        imageView = findViewById(R.id.img);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        //2.补间动画使用代码实现
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(5000);
        alphaAnimation.setRepeatMode(AlphaAnimation.REVERSE);
        alphaAnimation.setRepeatCount(AlphaAnimation.INFINITE);
        imageView.startAnimation(alphaAnimation);

    }

    @Override
    public void onClick(View v) {
        //1.补间动画xml实现
        switch (v.getId()){
            case R.id.btn1:
                imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.alpha_animation));
                break;
            case R.id.btn2:
                imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.rotate_animation));
                break;
            case R.id.btn3:
                imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
                break;
            case R.id.btn4:
                imageView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.translate_animation));

        }
    }
}