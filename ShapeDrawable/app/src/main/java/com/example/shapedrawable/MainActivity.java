package com.example.shapedrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int width = btn.getWidth();//在点击事件里可以get到宽高
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{100,100,100,100,100,100,100,100,100},null,null));//将左上，左下，右上，右下4个角的圆半径设为100,参数必须大于等于8
        LinearGradient linearGradient = new LinearGradient(0,0,width,0, Color.BLUE,Color.RED, Shader.TileMode.MIRROR);//设置线性渐变颜色
        shapeDrawable.getPaint().setShader(linearGradient);
        btn.setBackground(shapeDrawable);
		//Shader.TileMode.CLAMP会将边缘的像素进行拉伸、扩展到整个View的宽度或高度
		//Shader.TileMode.MIRROR在绘制的矩形区域内，X轴方向和Y轴方向上出现了镜面翻转 直到占满整个View的宽高
		//Shader.TileMode.REPEAT 将图像进行复制平铺 跟电脑桌面壁纸一样 占不满一屏会进行平铺
		//参考资料：https://www.jianshu.com/p/83af13b41bb6
    }
}