package com.aveteam.lorienzo9.istudy.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aveteam.lorienzo9.istudy.R;
import com.aveteam.lorienzo9.istudy.VerticalViewPager;
import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.VerticalViewPagerAdapter;

/**
 * Created by lorienzo9 on 19/09/17.
 */

public class CentralFragment extends Fragment {

    private VerticalViewPager verticalViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_viewpager, container, false);

        verticalViewPager = (VerticalViewPager)view.findViewById(R.id.vertical_viewpager);

        verticalViewPager.setAdapter(new VerticalViewPagerAdapter(3, getChildFragmentManager()));
        verticalViewPager.setCurrentItem(1);

        return view;
    }
}
