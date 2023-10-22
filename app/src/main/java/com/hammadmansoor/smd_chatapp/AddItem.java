package com.hammadmansoor.smd_chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AddItem extends AppCompatActivity {
    ImageView uploadImage,uploadVideo;
    EditText name,hourlyRate,description,city;
    Button postItem;
    private static final int PICK_IMAGES_REQUEST = 2;
    private ArrayList<Uri> selectedImageUris;
    private static final int PICK_VIDEOS_REQUEST = 4;
    private ArrayList<Uri> selectedVideoUris;
    List<String> downloadImageUris;
    List<String> downloadVideoUris;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        uploadImage = findViewById(R.id.uploadImage);
        uploadVideo = findViewById(R.id.uploadVideo);
        name = findViewById(R.id.name);
        hourlyRate = findViewById(R.id.hourlyRate);
        description = findViewById(R.id.description);
        postItem = findViewById(R.id.postItem);
        downloadImageUris = new ArrayList<>();
        downloadVideoUris = new ArrayList<>();
        city = findViewById(R.id.city);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        postItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentItemData rentItemData = new RentItemData(name.getText().toString(),description.getText().toString(),city.getText().toString(),Double.parseDouble(hourlyRate.getText().toString()),mAuth.getUid());
                DatabaseReference itemRef = database.getReference("Items");
                itemRef.push().setValue(rentItemData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(AddItem.this,"ITEM POSTED",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddItem.this,Home.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddItem.this,"Something went wrong",Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }



}