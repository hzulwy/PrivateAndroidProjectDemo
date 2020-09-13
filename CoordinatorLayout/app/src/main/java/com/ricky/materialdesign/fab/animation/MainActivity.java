package com.ricky.materialdesign.fab.animation;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements HideScrollListener{

	private RecyclerView recyclerview;
	private ImageButton fab;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		recyclerview = (RecyclerView)findViewById(R.id.recyclerview);
		fab = (ImageButton)findViewById(R.id.fab);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("Brett");
		
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			list.add("Item"+i);
		}
		RecyclerView.Adapter adapter = new FabRecyclerAdapter(list );
		recyclerview.setAdapter(adapter );
		
	}

	public void rotate(View v){
		Snackbar.make(v, "点击你好", Snackbar.LENGTH_SHORT)
				.setAction("确定", new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Hello World", 0).show();
					}
				})
				.show();
	}
	
	@Override
	public void onHide() {
		toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
		RelativeLayout.LayoutParams layoutParams = (LayoutParams) fab.getLayoutParams();
		fab.animate().translationY(fab.getHeight()+layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
	}

	@Override
	public void onShow() {
		toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));
		fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

	}

}
