package com.aveteam.lorienzo9.istudy;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Date date;
    private TextView numberdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = java.util.Calendar.getInstance().getTime();
        int number = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);

        numberdate = (TextView)findViewById(R.id.number_day);
        numberdate.setText(String.valueOf(number));
        Log.d("day", String.valueOf(number));
    }
}
