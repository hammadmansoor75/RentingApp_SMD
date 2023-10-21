package com.hammadmansoor.smd_chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class CreateProfile extends AppCompatActivity {
    EditText name,country,city,phoneNumber;
    FirebaseAuth mAuth;
    String selectedCity, selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        mAuth = FirebaseAuth.getInstance();

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
                String selectedCity = (String) citySpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
}