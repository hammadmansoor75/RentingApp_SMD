package com.hammadmansoor.smd_chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<ItemModel> itemList = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(itemList);

        rv.setAdapter(itemAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Create an instance of your ItemModel and add it to the list
                    ItemModel item = snapshot.getValue(ItemModel.class);
                    itemList.add(item);
                }
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle Home item click
                    return true;
                }
//                } else if (itemId == R.id.action_search) {
//                    startActivity(new Intent(Home.this, SearchScreen.class));
//                    return true;
//                } else if (itemId == R.id.action_profile) {
//                    // Handle Profile item click
//                    startActivity(new Intent(Home.this, ProfileScreen.class));
//                    return true;
//                } else if (itemId == R.id.action_chat) {
//                    // Handle Profile item click
//                    startActivity(new Intent(Home.this, ChatScreen.class));
//                    return true;
//                }
                else if (itemId == R.id.action_add) {
                    // Handle Profile item click
                    startActivity(new Intent(Home.this,AddItem.class));
                    return true;
                }
                return false;
            }
        });

    }
}