package com.example.materialdesign_recycler_itemtouchhelper;


import androidx.recyclerview.widget.RecyclerView;

public interface StartDragListener {
	/**
	 * 该接口用于需要主动回调拖拽效果的
	 * @param viewHolder
	 */
	public void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
