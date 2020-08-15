package com.example.showandhidetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvArrow;//要把图片的动画效果与文字的动画效果给区分开来
    private ImageView imArrow;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvArrow = findViewById(R.id.tv_arrow);
        imArrow = findViewById(R.id.im_arrow);

        tvArrow.setText("ValueAnimator 对象跟踪动画的时间，例如动画的已运行时长以及正在添加动画效果的属性的当前值。\n" +
                "\n" +
                "ValueAnimator 包含 TimeInterpolator 和 TypeEvaluator；前者用于定义动画插值，后者用于定义如何计算正在添加动画效果的属性的值。例如，在图 2 中，所用的 TimeInterpolator 为 AccelerateDecelerateInterpolator，所用的 TypeEvaluator 为 IntEvaluator。\n" +
                "\n" +
                "要开始动画，请创建一个 ValueAnimator，并为您想要添加动画效果的属性赋予起始值和结束值，以及动画时长。当您调用 start() 时，动画即会开始播放。在整个动画播放期间，ValueAnimator 将基于动画时长和已播放时长计算已完成动画分数（在 0 和 1 之间）。已完成动画分数表示动画已完成时间的百分比，0 表示 0%，1 表示 100%。以图 1 为例，在 t = 10ms 处，已完成动画分数将为 0.25，因为总时长 t = 40ms。");

        tvArrow.setHeight(0);

        imArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvArrow.clearAnimation();//每次点击图片都清除文字之前的动画效果
                final int startHeight = tvArrow.getHeight();//startHeight和myHeight只是方法中会用到，不应该归为成员变量
                final int myHeight;
                int duration = 2000;
                if(!flag){
                    RotateAnimation animation = new RotateAnimation(0,180,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(duration);
                    imArrow.startAnimation(animation);
                    myHeight = tvArrow.getLineCount()*tvArrow.getLineHeight() - startHeight;
                    flag = true;
                }else{
                    RotateAnimation animation = new RotateAnimation(180,0,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                    animation.setFillAfter(true);
                    animation.setDuration(duration);
                    imArrow.startAnimation(animation);
                    myHeight =  - startHeight;
                    flag = false;
                }
                Animation animation = new Animation(){
                    @Override
                    protected void applyTransformation(float interpolatedTime, Transformation t) {
                        super.applyTransformation(interpolatedTime, t);
                        tvArrow.setHeight((int) (startHeight+interpolatedTime*myHeight));
                    }
                };
                animation.setDuration(2000);
                tvArrow.startAnimation(animation);
            }
        });
    }
}

