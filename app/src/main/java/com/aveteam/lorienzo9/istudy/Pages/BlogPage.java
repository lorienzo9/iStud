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

import com.aveteam.lorienzo9.istudy.BlogAdapter;
import com.aveteam.lorienzo9.istudy.Constructors.Blog;
import com.aveteam.lorienzo9.istudy.DatabaseFirebase;
import com.aveteam.lorienzo9.istudy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by lorienzo9 on 29/09/17.
 */

public class BlogPage extends Fragment {
    RecyclerView recyclerView;
    BlogAdapter adapter;
    ArrayList<Blog> list = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference mRef;
    FloatingActionButton fab;
    DatabaseFirebase db;
    int bloc_post_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_room, container, false);  //Layout da sostituire
        firebaseAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("Groups").child("1").child("Blog");

        recyclerView = (RecyclerView)view.findViewById(R.id.list_of_messages); //Cambia id
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new BlogAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        //fab = (FloatingActionButton)view.findViewById(R.id.fab); //Verifica id
        ReadDb();

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Apriamo un'altra Activity
            }
        });*/


        return view;
    }


    public void ReadDb(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                try {
                    int i = 0;
                    for (DataSnapshot ds: dataSnapshot.getChildren()){

                        Blog value = dataSnapshot.child(String.valueOf(i)).getValue(Blog.class);
                        list.add(new Blog(value.getTitle(), value.getContent(), value.getUser_id(), value.getDate(), value.getType()));
                        adapter.notifyDataSetChanged();
                        i++;
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
