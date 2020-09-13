package com.ricky.materialdesign.fab.animation;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floatingactionbutton.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondActivity extends AppCompatActivity {

	private RecyclerView recyclerview;
	private Toolbar toolbar;
	private FloatingActionButton fab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		recyclerview = findViewById(R.id.recyclerview);
		fab = findViewById(R.id.fab);

		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setTitle("Brett");
		
		recyclerview.setLayoutManager(new LinearLayoutManager(this));
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 60; i++) {
			list.add("Item"+i);
		}
		RecyclerView.Adapter adapter = new FabRecyclerAdapter(list );
		recyclerview.setAdapter(adapter);
		
	}

}
