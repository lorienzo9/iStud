package com.aveteam.lorienzo9.istudy.Pages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lorienzo9 on 19/09/17.
 */

public class MainFragment extends android.support.v4.app.Fragment {
    private Date date;
    private TextView numberdate, monthdate, daydate;
    public String months[] = {
            "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
    };
    public String days[]={
            "Domenica", "Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato"
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        View included_layout = view.findViewById(R.id.date_layout);

        date = java.util.Calendar.getInstance().getTime();
        int number = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        setup(included_layout);

        numberdate.setText(String.valueOf(number));
        monthdate.setText(String.valueOf(months[month]));
        daydate.setText(String.valueOf(days[day]));


        return view;
    }
    public void setup(View included_layout){
        numberdate = (TextView)included_layout.findViewById(R.id.number_day);
        monthdate = (TextView)included_layout.findViewById(R.id.month);
        daydate = (TextView)included_layout.findViewById(R.id.day);
    }

}
