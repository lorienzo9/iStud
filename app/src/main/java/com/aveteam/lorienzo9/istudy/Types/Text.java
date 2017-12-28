package com.aveteam.lorienzo9.istudy.Types;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.aveteam.lorienzo9.istudy.BlogAdapter;
import com.aveteam.lorienzo9.istudy.Constructors.Blog;
import com.aveteam.lorienzo9.istudy.Constructors.Homework;
import com.aveteam.lorienzo9.istudy.MainActivity;
import com.aveteam.lorienzo9.istudy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by lorienzo9 on 14/12/17.
 */

public class Text extends AppCompatActivity {
    EditText title, content;
    FloatingActionButton floatingActionButton;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    int ID_NUMBER;
    String USER = new MainActivity().USER_ID;
    int GROUP_NUMBER = 1; //Da cercare dal database dell'utente che sta usando l'account

    //Rivedi integralment estruttura e stoccaggio dati nel db!!


    String TYPE = "text_format";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_creator);
        createObjects();
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("Groups");
        /*mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ID_NUMBER = (int)(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("text", "Failed to read value.", error.toException());
            }
        });*/

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titolo = title.getText().toString();
                String descrizione = content.getText().toString();
                mRef.child(String.valueOf(GROUP_NUMBER)).child("Blog").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ID_NUMBER = (int)(dataSnapshot.getChildrenCount()) + 1;
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("text", "Failed to read value.", error.toException());
                    }
                });
                if (titolo!=" " && descrizione!=" "){
                    //Invia nuovo post
                    Blog blog = new Blog(titolo, descrizione, USER, "11/12/2017", TYPE);
                    mRef.child(String.valueOf(ID_NUMBER)).setValue(blog);

                }
            }
        });

    }
    public void createObjects (){
        title = (EditText)findViewById(R.id.input_Title);
        content = (EditText)findViewById(R.id.content_Text);
        floatingActionButton  = (FloatingActionButton)findViewById(R.id.floatingActionButton);
    }

}
