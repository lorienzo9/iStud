package com.aveteam.lorienzo9.istudy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.ViewPagerAdapter;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Date date;
    private TextView numberdate;
    private ViewPager viewpager;

    //Combino il movimento di un verticalviewpager con quello di un normale viewpager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        viewpager = (ViewPager)findViewById(R.id.horizontal_viewpager);

        viewpager.setAdapter(new ViewPagerAdapter(3, getSupportFragmentManager()));
        viewpager.setCurrentItem(1);


    }

}
