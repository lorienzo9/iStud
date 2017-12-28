package com.aveteam.lorienzo9.istudy;

import android.app.Activity;
import android.content.Entity;
import android.util.Log;
import android.widget.Adapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 08/11/17.
 */

public  class DatabaseFirebase{
    FirebaseAuth auth;
    StorageReference mRef;
    FirebaseDatabase database;
    public void ReadDb(final String GROUP, DatabaseReference mRef, final ArrayList adapter){

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (int i = 0; i < dataSnapshot.getChildrenCount(); i++){
                        com.aveteam.lorienzo9.istudy.Constructors.Chat value = dataSnapshot.getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class);
                        com.aveteam.lorienzo9.istudy.Constructors.Chat chat = new com.aveteam.lorienzo9.istudy.Constructors.Chat();
                        String text = value.getText();
                        String user = value.getUser();
                        chat.setUser(user);
                        chat.setText(text);
                        adapter.add(chat);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("log", "Failed to read value.", error.toException());
            }
        });
    }
}
