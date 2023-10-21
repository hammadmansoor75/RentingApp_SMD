package com.hammadmansoor.smd_chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateProfile extends AppCompatActivity {
    EditText name,phoneNumber;
    FirebaseAuth mAuth;
    String selectedCity, selectedCountry;
    FirebaseDatabase database;
    Button createProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);

        Spinner countrySpinner = findViewById(R.id.countrySpinner);
        Spinner citySpinner = findViewById(R.id.citySpinner);
        String[] countries = {"Pakistan", "USA"};
        String[] citiesPakistan = {"ISLAMABAD", "RAWALPINDI"};
        String[] citiesUSA = {"NEW-YORK", "LOS-ANGELES"};

        final ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        final ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(countryAdapter);
        citySpinner.setAdapter(cityAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cityAdapter.clear();

                selectedCountry = countries[position];

                if (selectedCountry.equals("Pakistan")) {
                    cityAdapter.addAll(citiesPakistan);

                } else if (selectedCountry.equals("USA")) {
                    cityAdapter.addAll(citiesUSA);
                }

                cityAdapter.notifyDataSetChanged();
            }



            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCity = (String) citySpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        createProfile = findViewById(R.id.createProfile);
        DatabaseReference userReference = database.getReference("user");
        String uid = mAuth.getUid();

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(uid != null){
                                User user = new User(name.getText().toString(),mAuth.getCurrentUser().getEmail(),phoneNumber.getText().toString(),selectedCountry,selectedCity);
                                userReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(CreateProfile.this,Home.class));
                                    }
                                }).addOnCanceledListener(new OnCanceledListener() {
                                    @Override
                                    public void onCanceled() {
                                        Toast.makeText(CreateProfile.this,"Failed to create Profile",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }else{
                                Toast.makeText(CreateProfile.this,"Please Create an Account",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(CreateProfile.this,signup.class));
                            }
                        }
                    });
                }

        });




    }





}