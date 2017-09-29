package com.aveteam.lorienzo9.istudy.Constructors;

/**
 * Created by lorienzo9 on 29/09/17.
 */

public class Days {
    String day;
    boolean isSelected;
    public Days(String string, boolean isSelected){
        this.day = string;
        this.isSelected = isSelected;
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
