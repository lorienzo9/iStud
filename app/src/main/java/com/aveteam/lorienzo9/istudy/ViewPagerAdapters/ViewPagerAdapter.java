package com.aveteam.lorienzo9.istudy.ViewPagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aveteam.lorienzo9.istudy.Pages.CentralFragment;
import com.aveteam.lorienzo9.istudy.Pages.MainFragment;

/**
 * Created by lorienzo9 on 19/09/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int NumeroTab;

    public ViewPagerAdapter(int numberTab, FragmentManager fragmentManager){
        super(fragmentManager);
        this.NumeroTab = numberTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new MainFragment();
            case 1: return new CentralFragment();
            case 2: return new MainFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return NumeroTab;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}