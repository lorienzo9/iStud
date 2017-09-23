package com.aveteam.lorienzo9.istudy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.ViewPagerAdapter;

import java.util.Date;

public class MainActivity extends AppCompatActivity{
    private Date date;
    private TextView numberdate;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private TabLayout.Tab chat, main, other;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;

    //Aggiungere Tabs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        viewpager = (ViewPager)findViewById(R.id.horizontal_viewpager);

        viewpager.setAdapter(new ViewPagerAdapter(3, getSupportFragmentManager()));
        viewpager.setCurrentItem(1);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        chat = tabLayout.newTab().setIcon(R.drawable.pois);
        main = tabLayout.newTab().setIcon(R.drawable.pois);
        other = tabLayout.newTab().setIcon(R.drawable.pois);

        tabLayout.addTab(chat, 0);
        tabLayout.addTab(main, 1);
        tabLayout.addTab(other, 2);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewpager.addOnPageChangeListener(tabLayoutOnPageChangeListener);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
