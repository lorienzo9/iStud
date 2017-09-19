package com.aveteam.lorienzo9.istudy;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Lorenzo on 30/06/2017.
 */

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(Context context){
        super(context);
        init();
    }
    public VerticalViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init();
    }
    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
    private class VerticalPageTransformer implements PageTransformer{
        @Override
        public void transformPage(View page, float position) {
            if (position<-1){
                page.setAlpha(0);
            }
            else if (position<=1){
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
            } else {
                page.setAlpha(0);
            }
        }

    }
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXY(ev));
    }
}
//View for more https://stackoverflow.com/questions/13477820/android-vertical-viewpager

