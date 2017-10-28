package com.aveteam.lorienzo9.istudy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lorienzo9 on 28/10/17.
 */

public class SignUpActivity extends AppCompatActivity {
    EditText emailText, passwordText;
    Button login;
    FirebaseAuth firebaseAuth;
    String email, password;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);
        firebaseAuth = FirebaseAuth.getInstance(); //Inizializzo Firebase

        emailText = (EditText)findViewById(R.id.email_up);
        passwordText = (EditText)findViewById(R.id.password_up);
    }
    private void lunchdialog(){
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Do you want to create a new Account? Your email will be "+email+ " and your password "+password);
        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                createAccount();
            }
        });
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
    }
    public void createAccount(){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

    }
}
