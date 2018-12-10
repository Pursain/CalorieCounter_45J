package com.example.harrison.caloriecounter;

/*
This group consists of the following people

Crystal Lai Ton Nu  17316217
Ashley Ju           89693512
Katrin Martinez     38176707
Harrison Huang      48425701

*/

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference refUsers;
    private ArrayList<String> arrayOfUsernames;
    private EditText editTextUserID;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        refUsers = database.getReference("users");
        arrayOfUsernames = new ArrayList<>();

        refUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingMain", "Adding " + dataSnapshot.getKey() + " to the array");
                arrayOfUsernames.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                arrayOfUsernames.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }

    public void onClickSignIn(View view){
        editTextUserID = (EditText) findViewById(R.id.text_userID);
        userID = editTextUserID.getText().toString();
        Log.d("testingMain", "Sign In button clicked as " + userID);

        if (!arrayOfUsernames.contains(userID)){
            refUsers.child(userID).setValue("");
            Log.d("testingMain", "Created new user");
        }

        Intent intent = new Intent(this, UserView.class);
        intent.putExtra("username", userID);
        startActivity(intent);
    }
}