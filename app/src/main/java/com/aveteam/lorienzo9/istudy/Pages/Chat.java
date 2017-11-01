package com.aveteam.lorienzo9.istudy.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aveteam.lorienzo9.istudy.AdapterChat;
import com.aveteam.lorienzo9.istudy.Constructors.Users;
import com.aveteam.lorienzo9.istudy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 01/11/17.
 */

public class Chat extends Fragment {
    EditText input_text;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    AdapterChat adapterChat;
    ArrayList<com.aveteam.lorienzo9.istudy.Constructors.Chat> userses = new ArrayList<>();
    DatabaseReference mRef;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userID;
    final static String TAG = Chat.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_room, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference();
        userID = firebaseAuth.getCurrentUser().toString();


        //Add read database and load data into recyclerview

        //Bisogna usare un altro constructor!!!!!!!

        //Inizializzo la RecyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.list_of_messages);
        recyclerView.setHasFixedSize(true);
        adapterChat = new AdapterChat(userID, getContext(), userses);
        recyclerView.setAdapter(adapterChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        com.aveteam.lorienzo9.istudy.Constructors.Chat chatConstructor = new com.aveteam.lorienzo9.istudy.Constructors.Chat();
                        chatConstructor.setText(ds.child("groups").child(userID).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getText());
                        chatConstructor.setUser(ds.child("groups").child(userID).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getUser());

                        userses.add(new com.aveteam.lorienzo9.istudy.Constructors.Chat(chatConstructor.getUser(), chatConstructor.getText()));
                        adapterChat.notifyDataSetChanged();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        View text_layou = view.findViewById(R.id.textInputLayout);
        input_text = (EditText)text_layou.findViewById(R.id.input);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input_text.getText().toString();
                userses.add(new com.aveteam.lorienzo9.istudy.Constructors.Chat(userID, text));
                mRef.child("groups").child("Gruppo1").setValue(userses);
                adapterChat.notifyDataSetChanged();
            }
        });

        return view;
    }
}
