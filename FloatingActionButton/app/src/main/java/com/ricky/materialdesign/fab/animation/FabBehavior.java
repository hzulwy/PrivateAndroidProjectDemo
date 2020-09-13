package com.ricky.materialdesign.fab.animation;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabBehavior extends FloatingActionButton.Behavior {
	private boolean visible = true;//是否可见

	public FabBehavior(Context context, AttributeSet attrs) {//注意:自定义view需要重写3个构造函数否则回报错Caused by: java.lang.NoSuchMethodException: <init> [class android.content.Context, interface android.util.AttributeSet]
		super(context, attrs);
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
									   FloatingActionButton child, View directTargetChild, View target,
									   int nestedScrollAxes) {
		// 当观察的View（RecyclerView）发生滑动的开始的时候回调的
		//nestedScrollAxes:滑动关联轴， 我们现在只关心垂直的滑动。
		return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild,
				target, nestedScrollAxes);
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout,
							   FloatingActionButton child, View target, int dxConsumed,
							   int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
				dxUnconsumed, dyUnconsumed);
		// 当观察的view滑动的时候回调的
		//根据情况执行动画
		if(dyConsumed>0&&visible){
			//show
			visible = false;
			onHide(child);
		}else if(dyConsumed<0){
			//hide
			visible = true;
			onShow(child);
		}

	}

	public void onHide(FloatingActionButton fab) {
		ViewCompat.animate(fab).scaleX(0f).scaleY(0f).start();
	}

	public void onShow(FloatingActionButton fab) {
		ViewCompat.animate(fab).scaleX(1f).scaleY(1f).start();
	}

}
