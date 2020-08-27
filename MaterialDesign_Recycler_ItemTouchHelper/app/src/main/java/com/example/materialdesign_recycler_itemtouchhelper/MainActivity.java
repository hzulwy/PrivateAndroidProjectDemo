package com.example.materialdesign_recycler_itemtouchhelper;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity implements StartDragListener{

	private RecyclerView recyclerView;
	private ItemTouchHelper itemTouchHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		recyclerView = (RecyclerView)findViewById(R.id.recyclerview); 
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		
		List<QQMessage> list = DataUtils.init();
		QQAdapter adapter = new QQAdapter(list,this);
		recyclerView.setAdapter(adapter);
		//条目触摸帮助类
		ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter);
		itemTouchHelper = new ItemTouchHelper(callback);
		itemTouchHelper.attachToRecyclerView(recyclerView);
		
	}

	@Override
	public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
		itemTouchHelper.startDrag(viewHolder);
	}

}
