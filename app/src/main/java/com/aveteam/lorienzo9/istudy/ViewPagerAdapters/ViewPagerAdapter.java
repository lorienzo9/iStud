package com.aveteam.lorienzo9.istudy.ViewPagerAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aveteam.lorienzo9.istudy.Pages.Chat;
import com.aveteam.lorienzo9.istudy.Pages.HomePage;
import com.aveteam.lorienzo9.istudy.Pages.MainFragment;
import com.aveteam.lorienzo9.istudy.Pages.ToDo;

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
            case 0: return new Chat();
            case 1: return new HomePage();
            case 2: return new HomePage();
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