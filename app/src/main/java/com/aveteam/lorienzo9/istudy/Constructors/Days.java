package com.aveteam.lorienzo9.istudy.Constructors;

import android.graphics.Color;

/**
 * Created by lorienzo9 on 29/09/17.
 */

public class Days {
    String day;
    boolean isSelected;
    int color;
    public Days(String string, boolean isSelected, int color){
        this.day = string;
        this.isSelected = isSelected;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
