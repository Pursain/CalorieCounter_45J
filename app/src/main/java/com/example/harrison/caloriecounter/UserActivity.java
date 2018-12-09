package com.example.harrison.caloriecounter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserActivity extends AppCompatActivity {

    private DatabaseReference refFoodCalories;
    private DatabaseReference refUser;
    private FirebaseDatabase database;
    private ChildEventListener userEventListener;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_view);
        //Intent intent = getIntent();
       // String value = intent.getStringExtra("username");

        user = new User("Kelly123");

        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("users/" + user.getUsername());
        userEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("refUser: ", dataSnapshot.toString());
                user.addDate(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        refUser.addChildEventListener(userEventListener);


        /*
        adding a food
        refFoodCalories.child("pears").setValue("15");

        removing a food
        refFoodCalories.child("pears").removeValue();

        */
    }
}