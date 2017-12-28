package com.aveteam.lorienzo9.istudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.Constructors.Blog;
import com.aveteam.lorienzo9.istudy.Constructors.User;
import com.aveteam.lorienzo9.istudy.Constructors.Users;
import com.aveteam.lorienzo9.istudy.Pages.HomePage;
import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity{
    private Date date;
    private final int NUMBER_OF_TABS = 5;
    private TextView numberdate;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private TabLayout.Tab chat, main, other, profile, add;
    private TabLayout.TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener;
    private Toolbar toolbar;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference mRef;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth.AuthStateListener mAuthListener;
    String TAG = "authListener";
    FirebaseUser user;
    String userID;
    Users users;
    public String USER_ID = "lorienzo9";
    public static String user_nickname;
    String ID_NICKNAME = new LoginActivity().readSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        View view = findViewById(R.id.big_appbar);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        mRef = mFirebaseDatabase.getInstance().getReference().child("users");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        Intent intent = getIntent();
        USER_ID = intent.getStringExtra("user");

        //mRef.child("users").child(userID).setValue("prova");


        // Read from the database
        mRef.child(ID_NICKNAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "Value is: " );
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        user = firebaseAuth.getCurrentUser();
        userID = user.toString();
        users = new Users(userID);
        //mRef.child("users").setValue(users);


        setSupportActionBar(toolbar);

        viewpager = (ViewPager)findViewById(R.id.horizontal_viewpager);

        viewpager.setAdapter(new ViewPagerAdapter(NUMBER_OF_TABS, getSupportFragmentManager()));
        //viewpager.setCurrentItem(0);
        viewpager.setCurrentItem(1);

        tabLayout = (TabLayout)findViewById(R.id.tab);
        add = tabLayout.newTab().setIcon(R.drawable.ic_chat_black_24dp);
        profile = tabLayout.newTab().setIcon(R.drawable.pois);
        chat = tabLayout.newTab().setIcon(R.drawable.ic_chat_black_24dp);
        main = tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp);
        other = tabLayout.newTab().setIcon(R.drawable.ic_public_black_24dp);


        tabLayout.addTab(other, 0);
        tabLayout.addTab(chat, 1);
        tabLayout.addTab(add, 2);
        tabLayout.addTab(main, 3);
         //Blog
        tabLayout.addTab(profile, 4);


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
        builder = new AlertDialog.Builder(MainActivity.this);
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
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
