package com.java.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.BottomNavigationViewHelper;
import com.activity.fragment.Bug51Fragment;
import com.activity.fragment.Bug52Fragment;
import com.activity.fragment.Bug53Fragment;
import com.activity.fragment.Bug54Fragment;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ViewpagerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        viewPager = findViewById(R.id.viewpager);
        initFragments();
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
    }

    private void initFragments() {

        fragments = new Fragment[]{Bug51Fragment.newIntance("第一页"), Bug52Fragment.newIntance("第二页"), Bug53Fragment.newIntance("第三页"), Bug54Fragment.newIntance("第四页")};
    }

    static class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        Fragment[] fragments;

        public MyViewPagerAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //TODO: 第三步 添加NavigationItemSelected监听
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.fragment_1:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.fragment_2:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.fragment_3:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.fragment_4:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }

    };


}
