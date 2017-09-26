package com.aveteam.lorienzo9.istudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by lorienzo9 on 24/09/17.
 */

public class LoginActivity extends AppCompatActivity {
    EditText emailText, passwordText;
    Button login;
    FirebaseAuth firebaseAuth;
    String email, password;
    private SharedPreferences sharedPreferences;
    final static String prefEmail = "prefEmail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        firebaseAuth = FirebaseAuth.getInstance(); //Inizializzo Firebase


        emailText = (EditText)findViewById(R.id.editText);
        passwordText = (EditText)findViewById(R.id.editText3);
        login = (Button)findViewById(R.id.button2);


        if(readSharedPreferences()!=null){ //Verifico SP
            emailText.setText(readSharedPreferences());
        }else{ //Molto opzionale
            Toast.makeText(getApplicationContext(), "Inserisci le tue credenziali", Toast.LENGTH_LONG).show();
        }


        login.setOnClickListener(new View.OnClickListener() { //E' il click sul bottone: prima autentica, poi salva le SP
            @Override
            public void onClick(View view) {
                authenicate();
                if (readSharedPreferences()==null){
                    WriteStringPreferences(email);
                }
            }
        });
    }


    public String readSharedPreferences(){  //Legge dalle SP con tag prefEmail e prende solo la stringa "email"
        sharedPreferences = getSharedPreferences(prefEmail, MODE_PRIVATE);
        return sharedPreferences.getString("email", null);
    }


    public void WriteStringPreferences(String email){ //Scrive le SP con tag prefEmail aggiungendo al string "email"
        sharedPreferences = getSharedPreferences(prefEmail, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }


    public void authenicate(){  //Permette l'autenticazione login con firebase
        email = emailText.getText().toString().trim();
        password = passwordText.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
                else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
