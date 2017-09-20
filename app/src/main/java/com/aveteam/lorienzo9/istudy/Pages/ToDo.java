package com.aveteam.lorienzo9.istudy.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lorienzo9 on 20/09/17.
 */

public class ToDo extends Fragment {
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    TabLayout.Tab day1, day2, day3, day4;
    Calendar date;
    int datetry;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo, container, false);

        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        day1 = tabLayout.newTab().setText(day(1));
        tabLayout.addTab(day1);

        day2 = tabLayout.newTab().setText(day(2));
        tabLayout.addTab(day2);

        day3 = tabLayout.newTab().setText(day(3));
        tabLayout.addTab(day3);

        day4 = tabLayout.newTab().setText(day(4));
        tabLayout.addTab(day4);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Aggiorno la recyclerView
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
    public String day(int numb){
        date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, numb);
        datetry = date.get(Calendar.DATE);
        return String.valueOf(datetry)+" / "+String.valueOf(date.get(Calendar.MONTH)+1);
    }
}
