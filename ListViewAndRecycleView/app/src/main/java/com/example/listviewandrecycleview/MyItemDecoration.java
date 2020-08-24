package com.example.listviewandrecycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int[] attrs= new int[]{
            android.R.attr.listDivider//可以在styles文件中设置<item name="android:listDivider"></item>改变默认样式
    };

    public MyItemDecoration(Context context, int orientation) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if(orientation!=LinearLayoutManager.HORIZONTAL&&orientation!=LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("哥们,逗我ma?非水平和线性的枚举类型");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //2.调用这个绘制方法， RecyclerView会毁掉该绘制方法,需要你自己去绘制条目的间隔线
        if(mOrientation == LinearLayoutManager.VERTICAL){//垂直
            drawVertical(c,parent);
        }else{//水平
            drawHorizontal(c,parent);
        }

        super.onDraw(c, parent, state);
    }


    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 绘制水平间隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin-parent.getPaddingLeft();
            int right = child.getRight()+ params.rightMargin+parent.getPaddingRight();
            int top = child.getBottom() + params.bottomMargin+parent.getPaddingBottom();
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }


    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //绘制垂直间隔线(垂直的矩形)
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin+parent.getPaddingRight();
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin-parent.getPaddingTop();
            int bottom = child.getBottom() + params.bottomMargin+parent.getPaddingBottom();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //1.调用此方法（首先会先获取条目之间的间隙宽度---Rect矩形区域）
        // 获得条目的偏移量(所有的条目都回调用一次该方法)
        if(mOrientation == LinearLayoutManager.VERTICAL){//垂直
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else{//水平
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0 );//设置分割线的位置在右边
        }
    }
}
