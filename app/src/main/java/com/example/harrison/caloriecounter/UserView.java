package com.example.harrison.caloriecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserView extends AppCompatActivity {

    private DatabaseReference refFoodCalories;
    private DatabaseReference refUser;
    private FirebaseDatabase database;
    private ChildEventListener userEventListener;
    private ArrayAdapter<String> dateAdapter;
    private ListView listView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);


        user = new User("Joe");

        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("users/" + user.getUsername());

        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, user.getArrayOfDatesStrings());
        listView = findViewById(R.id.list_view_User);
        listView.setAdapter(dateAdapter);


        userEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingUserView", dataSnapshot.toString());
                user.addDate(dataSnapshot);     //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }
                dateAdapter.notifyDataSetChanged();
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
    }

    public void moveToDateView(View view){
        Intent intent = new Intent(this, DateView.class);
        startActivity(intent);
    }
}
