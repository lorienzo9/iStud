package com.aveteam.lorienzo9.istudy.Pages;

import android.accessibilityservice.AccessibilityService;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aveteam.lorienzo9.istudy.AdapterChat;
import com.aveteam.lorienzo9.istudy.Constructors.User;
import com.aveteam.lorienzo9.istudy.Constructors.Users;
import com.aveteam.lorienzo9.istudy.MainActivity;
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
    EditText input_text, code_group;
    TextView message;
    Button send_code;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    AdapterChat adapterChat;
    ArrayList<com.aveteam.lorienzo9.istudy.Constructors.Chat> userses = new ArrayList<>();
    DatabaseReference mRef;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String userID;
    ConstraintLayout layout;
    final static String TAG = Chat.class.getSimpleName();
    String GRUPPO;
    int ID_NUMBER;
    final String USER_ID = new MainActivity().USER_ID;
    String user_nick;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_room, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRef = firebaseDatabase.getReference().child("groups").child("Gruppo1");
        //getUserInformation();





        recyclerView = (RecyclerView)view.findViewById(R.id.list_of_messages);
        input_text = (EditText)view.findViewById(R.id.input);
        fab = (FloatingActionButton)view.findViewById(R.id.fab);


        //Inizializzo la RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterChat = new AdapterChat("lorienzo9", getActivity(), userses); //Modifica userID
        recyclerView.setAdapter(adapterChat);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    userses.clear();
                    int i = 0;
                    //for (int i = 0; i < dataSnapshot.getChildrenCount(); i++){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        com.aveteam.lorienzo9.istudy.Constructors.Chat chatConstructor = new com.aveteam.lorienzo9.istudy.Constructors.Chat();
                        chatConstructor.setText(dataSnapshot.child(String.valueOf(i)).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getText());
                        chatConstructor.setUser(dataSnapshot.child(String.valueOf(i)).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getUser());

                        userses.add(new com.aveteam.lorienzo9.istudy.Constructors.Chat(chatConstructor.getUser(), chatConstructor.getText()));
                        adapterChat.notifyDataSetChanged();
                        recyclerView.scrollToPosition(userses.size()-1); //Da verificare
                        i++;
                        ID_NUMBER = i;
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









        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input_text.getText().toString();
                input_text.setText("");
                mRef.child(String.valueOf(ID_NUMBER)).setValue(new com.aveteam.lorienzo9.istudy.Constructors.Chat(user_nick, text));

            }
        });
        /*send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GRUPPO = code_group.getText().toString();
                InizializeAll(GRUPPO);
            }
        });*/

        return view;
    }



    public void getUserInformation(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(USER_ID).getValue(User.class);
                //user_nick = user.getName();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    //Questa funzione non mi serve qui ma quando creo un gruppo torna utile
    public void verifyGroup() {
        mRef.child("groups").child(GRUPPO).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void InizializeAll(String GROUP){
        ReadDb(GROUP);
    }


    public void selectViewMode(){

        //Inizializzo la modalità di visualizzazione
        if (adapterChat.getItemCount()==0){
            layout.setBackgroundResource(R.drawable.pois);//Attento ad aggiornare quando aggiungi un nuovo dato
            recyclerView.setVisibility(View.GONE);
            input_text.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
        }

    }

    public void ReadDb(final String GROUP){
        //Leggo il db
        userses.clear();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    int i = 0;
                    //for (int i = 0; i < dataSnapshot.getChildrenCount(); i++){
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        com.aveteam.lorienzo9.istudy.Constructors.Chat chatConstructor = new com.aveteam.lorienzo9.istudy.Constructors.Chat();
                        chatConstructor.setText(dataSnapshot.child("groups").child("Gruppo1").child(String.valueOf(i)).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getText());
                        chatConstructor.setUser(dataSnapshot.child("groups").child("Gruppo1").child(String.valueOf(i)).getValue(com.aveteam.lorienzo9.istudy.Constructors.Chat.class).getUser());

                        userses.add(new com.aveteam.lorienzo9.istudy.Constructors.Chat(chatConstructor.getUser(), chatConstructor.getText()));
                        adapterChat.notifyDataSetChanged();
                        i++;
                        ID_NUMBER = i;
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
    }
}
