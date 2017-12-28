package com.aveteam.lorienzo9.istudy.SignUpPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aveteam.lorienzo9.istudy.R;
import com.aveteam.lorienzo9.istudy.SignUpActivity;
import com.aveteam.lorienzo9.istudy.SignUpPager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by lorienzo9 on 19/12/17.
 */

public class InsertEmailAndNick extends Fragment {
    public EditText nickname, email;
    Button next;
    String nick, mail;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mRef;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nick_email_signup, container, false);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("Users");

        nickname = (EditText)view.findViewById(R.id.nickname_signup);
        next = (Button)view.findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nick = nickname.getText().toString().trim();

                if (nick != ""){
                    //Come faccio?
                    mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            if (dataSnapshot.child(nick).exists()){
                                nick = "";
                            }else {
                                //Creo nuovo utente
                                mRef.setValue(nick);
                                SignUpPager upActivity = new SignUpPager();
                                upActivity.viewpager.setCurrentItem(1); //Chissà...
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("mex", "Failed to read value.", error.toException());
                    }
                });
                }
            }
        });

        return view;
    }
}
