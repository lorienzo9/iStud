package com.aveteam.lorienzo9.istudy;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aveteam.lorienzo9.istudy.Constructors.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by lorienzo9 on 28/10/17.
 */

public class SignUpActivity extends Activity {
    EditText emailText, passwordText, nicknameText;
    Button signup;
    FirebaseAuth firebaseAuth;
    String email, password, nickname;
    AlertDialog.Builder builder;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    String userID;
    String TAG = "message";
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        firebaseAuth = FirebaseAuth.getInstance(); //Inizializzo Firebase

        mRef = mFirebaseDatabase.getInstance().getReference().child("Users");

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
                // ...
            }
        };


        emailText = (EditText)findViewById(R.id.email_signup);
        passwordText = (EditText)findViewById(R.id.password_up);
        nicknameText = (EditText)findViewById(R.id.nickname_up);
        signup = (Button)findViewById(R.id.next);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailText.getText().toString().trim();
                password = passwordText.getText().toString().trim();
                nickname = nicknameText.getText().toString().trim();
                lunchdialog();
            }
        });
    }
    public void lunchdialog(){
        builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setMessage("Do you want to create a new Account? Your email will be "+email+ ", your password "+password+" and your nickname will be "+nickname);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                createAccount();
                //mRef.child("users").setValue(userID);
            }
        });
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }
    public void createAccount(){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                } else {
                    if (nickname != null){
                        mRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    if (dataSnapshot.child(nickname).exists()){
                                        nickname = "";
                                    }else {
                                        //Creo nuovo utente
                                        mRef.setValue(nickname);
                                        verify();
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
                    //verify();
                    //addUserInfoinDatabase("lorienzo9"); //Aggiungi un editText da cui atingere il valore
                    //finish();child("uesrs")
                }
            }
        });
        //potrei creare qui un nuovo utente, oppure creare un costructor User
        User user = new User(nickname, "Gruppo1", email);
        mRef.child(email).setValue(user);
    }
    public void verify(){
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener(){
                    @Override
                    public void onComplete(@NonNull Task task) {
                        // Re-enable button
                        findViewById(R.id.next).setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("exc", "sendEmailVerification", task.getException());
                            Toast.makeText(SignUpActivity.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
