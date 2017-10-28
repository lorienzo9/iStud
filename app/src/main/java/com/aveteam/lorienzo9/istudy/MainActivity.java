package com.aveteam.lorienzo9.istudy;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.Pages.HomePage;
import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class MainActivity extends AppCompatActivity{
    private Date date;
    private TextView numberdate;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private TabLayout.Tab chat, main, other, profile, add;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
    private Toolbar toolbar;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        View view = findViewById(R.id.big_appbar);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);

        viewpager = (ViewPager)findViewById(R.id.horizontal_viewpager);

        viewpager.setAdapter(new ViewPagerAdapter(3, getSupportFragmentManager()));
        viewpager.setCurrentItem(0);
        viewpager.setCurrentItem(1);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        add = tabLayout.newTab().setIcon(R.drawable.ic_chat_black_24dp);
        profile = tabLayout.newTab().setIcon(R.drawable.pois);
        chat = tabLayout.newTab().setIcon(R.drawable.ic_chat_black_24dp);
        main = tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp);
        other = tabLayout.newTab().setIcon(R.drawable.ic_public_black_24dp);

        tabLayout.addTab(chat, 0);
        tabLayout.addTab(main, 1);
        tabLayout.addTab(other, 2);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //Da sistemare lo swipe non molto reattivo e preciso
        tabLayoutOnPageChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewpager.addOnPageChangeListener(tabLayoutOnPageChangeListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.logout: logOut();
               return true;
       }
       return true;
    }
    public void logOut(){
        builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Desideri uscire?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginActivity page = new LoginActivity();
                page.WriteStringPreferences("");
                onDestroy();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
