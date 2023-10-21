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

public class signup extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button signUpButton;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();


        signUpButton = findViewById(R.id.signupButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signup.this,"Account Created!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(signup.this,CreateProfile.class));
                            }
                        })
                        .addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {
                                Toast.makeText(signup.this,"Something went wrong",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        TextView login = findViewById(R.id.loginClickable);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}