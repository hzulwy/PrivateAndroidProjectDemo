package com.example.coordinatorlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ricky.materialdesign.fab.animation.R;

public class MainActivity extends AppCompatActivity {

	private TabLayout tabLayout;
	private String[] title = {
			"头条",
			"新闻",
			"娱乐",
			"体育",
			"科技",
			"美女",
			"财经",
			"汽车",
			"房子",
			"头条"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		final ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
		tabLayout = (TabLayout)findViewById(R.id.tablayout);
		MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
		//1.TabLayout和Viewpager关联
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

			@Override
			public void onTabUnselected(TabLayout.Tab arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				// 被选中的时候回调
				viewPager.setCurrentItem(tab.getPosition(),true);
			}

			@Override
			public void onTabReselected(TabLayout.Tab arg0) {
				// TODO Auto-generated method stub

			}
		});
		//2.ViewPager滑动关联tabLayout
		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


		//设置tabLayout的标签来自于PagerAdapter
		tabLayout.setTabsFromPagerAdapter(adapter);

		viewPager.setAdapter(adapter);
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return title[position];
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new NewsDetailFragment();
			Bundle bundle = new Bundle();
			bundle.putString("title", title[position]);
			f.setArguments(bundle);
			return f;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return title.length;
		}
	}
}
