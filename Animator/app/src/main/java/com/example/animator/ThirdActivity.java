package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        iv = (ImageView)findViewById(R.id.button1);
//		iv.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(getApplicationContext(), "点我干啥？", 0).show();
//			}
//		});

    }

    int position = 0;
    public void startAnimation(View v){

        //属性动画,没有动画效果，瞬间就改变属性值
//		position += 100;
//		v.setTranslationX(position);
//		v.setAlpha((float)Math.random());

        //1.---------------------------------------
        /**
         * float... values: A set of values that the animation will animate between over time.

         */
//		ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationX", 0f,300f);
//		oa.setDuration(500);
//		oa.start();
//		ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationY", 0f,300f);
//		oa.setDuration(500);
//		oa.start();
//		ObjectAnimator oa = ObjectAnimator.ofFloat(v, "rotationX", 0f,360f);
//		oa.setDuration(500);
//		oa.start();

        //2.----------------多个动画同时执行---办法1：设置动画监听，开始第一个动画同时开启其他动画-----------------
        //方法1：
//		ObjectAnimator animator = ObjectAnimator.ofFloat(v, "haha", 0f, 100f);//没有这个属性的时候，就是valueanimator，ObjectAnimator继承于valueanimator
//		animator.setDuration(300);
//		//设置动画监听,数据更新会被调用
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				//动画在执行的过程当中，不断地调用此方法
////				animation.getAnimatedFraction()//百分比
//				//得到duration时间内 values当中的某一个中间值。0f~100f
//				float value = (float) animation.getAnimatedValue();//
//				iv.setScaleX(0.5f+value/200);//0.5~1
//				iv.setScaleY(0.5f+value/200);//0.5~1
//			}
//		});
//		animator.start();

//		animator.addListener(new AnimatorListener() {
//
//			@Override
//			public void onAnimationStart(Animator animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animator animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animator animation) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onAnimationCancel(Animator animation) {
//				// TODO Auto-generated method stub
//
//			}
//		});

        //2)方法2：用ValueAnimator
//		ValueAnimator animator = ValueAnimator.ofFloat(0f,200f);
//		animator.setDuration(200);
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
////				//动画在执行的过程当中，不断地调用此方法
//////			animation.getAnimatedFraction()//百分比
////			//得到duration时间内 values当中的某一个中间值。0f~100f
//				float value = (float) animation.getAnimatedValue();//
//				iv.setScaleX(0.5f+value/200);//0.5~1
//				iv.setScaleY(0.5f+value/200);//0.5~1
//			}
//		});
//		animator.start();
        //3）方法3
        //float... values:代表关键帧的值
//		PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f,0.7f,1f);
//		PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f,0.7f,1f);
//		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f,0.7f,1f);
////		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("translationX", 0f,300f);
//
//		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv, holder1,holder2,holder3);
//		animator.setDuration(1000);
//		animator.addUpdateListener(new AnimatorUpdateListener() {
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				// TODO Auto-generated method stub
//				float animatedValue = (float) animation.getAnimatedValue();
//				float animatedFraction = animation.getAnimatedFraction();
//				long playTime = animation.getCurrentPlayTime();
//
//				System.out.println("animatedValue:"+animatedValue+",  playTime:"+playTime);
//			}
//		});
//		animator.start();
        //4)方法4：-----------------动画集合--------------------
//		ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv,"alpha", 1f,0.7f,1f);
//		ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv,"scaleX", 1f,0.7f,1f);
//		ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv,"scaleY", 1f,0.7f,1f);
//
//		AnimatorSet animatorSet = new AnimatorSet();
//		animatorSet.setDuration(500);
////		animatorSet.play(anim);//执行当个动画
////		animatorSet.playTogether(animator1,animator2,animator3);//同时执行
//		animatorSet.playSequentially(animator1,animator2,animator3);//依次执行动画
//		animatorSet.start();

        //4.-------------------案例：实现抛物线效果--------------------------
//		/**
//		 * x:匀速
//		 * y:加速度   y=1/2*g*t*t
//		 * 使用估值器最好实现。
//		 */
//		ValueAnimator valueAnimator = new ValueAnimator();
//		valueAnimator.setDuration(4000);
////		valueAnimator.setFloatValues(values)
//		valueAnimator.setObjectValues(new PointF(0, 0));
//		//估值器---定义计算规则
//		valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
//
//			@Override
//			public PointF evaluate(float fraction, PointF startValue,
//					PointF endValue) {
//				//拿到每一个时间点的坐标
//				//x=v*t (s秒)
//				PointF pointF = new PointF();
//				pointF.x = 100f*(fraction*4);//初始速度*(执行的百分比*4)
////				pointF.y = 0.5f*9.8f*(fraction*4)*(fraction*4);
//				pointF.y = 0.5f*150f*(fraction*4)*(fraction*4);
//				return pointF;
//			}
//		});
//
//		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {//每执行一次该方法都会调用一次evaluate方法
//
//			@Override
//			public void onAnimationUpdate(ValueAnimator animation) {
//				//得到此时间点的坐标
//				PointF pointF = (PointF) animation.getAnimatedValue();//拿到估值器设置的值
//
//				iv.setX(pointF.x);
//				iv.setY(pointF.y);
//			}
//		});
//		valueAnimator.start();


        ObjectAnimator oa = ObjectAnimator.ofFloat(v, "translationY", 0f,1100f);
        oa.setDuration(500);
        //设置加速器---
//		oa.setInterpolator(new AccelerateInterpolator(5));
//		oa.setInterpolator(new AccelerateDecelerateInterpolator());
//		oa.setInterpolator(new AnticipateInterpolator(8));
//		oa.setInterpolator(new OvershootInterpolator());
//		oa.setInterpolator(new CycleInterpolator(4));
        oa.setInterpolator(new BounceInterpolator());

        oa.start();
    }

    //使用xml的方式创建属性动画,一般不用
    public void playxml(View v){

        ObjectAnimator oa = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.oanimator);//必须放在animator文件夹下面
        //设置执行目标
        oa.setTarget(iv);
        oa.start();//开始执行
    }
}