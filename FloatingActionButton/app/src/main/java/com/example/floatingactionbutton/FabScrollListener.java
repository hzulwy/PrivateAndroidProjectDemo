package com.example.floatingactionbutton;


import androidx.recyclerview.widget.RecyclerView;

public class FabScrollListener extends RecyclerView.OnScrollListener {
	private static final int THRESHOLD = 20;
	private int distance = 0;
	private HideScrollListener hideListener;
	private boolean visible = true;//�Ƿ�ɼ�
	
	public FabScrollListener(HideScrollListener hideScrollListener) {
		// TODO Auto-generated constructor stub
		this.hideListener = hideScrollListener;
	}
	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);
		/**
		 * dy:Y轴方向的增量
		 * 有正和负
		 * 当正在执行动画的时候，就不要再执行了
		 */
		if(distance>THRESHOLD&&visible){
			//隐藏动画
			visible = false;
			hideListener.onHide();
			distance = 0;
		}else if(distance<-20&&!visible){
			//显示动画
			visible = true;
			hideListener.onShow();
			distance = 0;
		}
		if(visible&&dy>0||(!visible&&dy<0)){
			distance += dy;
		}
	}

}
