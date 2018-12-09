package com.example.harrison.caloriecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference refTesting;
    private ArrayList<String> arrayOfUsernames;
    private ArrayAdapter<String> usernameAdapter;
    private ListView listView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        refTesting = database.getReference("users");

        arrayOfUsernames = new ArrayList<>();
        usernameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayOfUsernames);
        listView = findViewById(R.id.listView2);
        listView.setAdapter(usernameAdapter);

        refTesting.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testing", dataSnapshot.getKey());
                usernameAdapter.add(dataSnapshot.getKey());
                Log.d("testing::", arrayOfUsernames.toString());
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
        });

        }



        public void moveToUserView(View view){
            Log.d("testing:::", "hello");

            Intent intent = new Intent(this, UserView.class);
            startActivity(intent);
        }
}