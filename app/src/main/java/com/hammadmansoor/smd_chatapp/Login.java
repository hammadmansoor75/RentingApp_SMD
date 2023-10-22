package com.hammadmansoor.smd_chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button login;
    TextView signup;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        signup = findViewById(R.id.signupClickable);
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
            }
        });

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Login.this,Home.class));
                            }
                        })
                        .addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(Login.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}