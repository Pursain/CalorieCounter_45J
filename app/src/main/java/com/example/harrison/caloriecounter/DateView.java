package com.example.harrison.caloriecounter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DateView extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference refDate;
    private ChildEventListener dateEventListener;
    private ArrayAdapter<String> foodAdapter;
    private ListView listView;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_view);



        date = new Date("12-3-18");

        database = FirebaseDatabase.getInstance();
        refDate = database.getReference("users/Joe/" + date.getDate());

        foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, date.getArrayOfFoodsStrings());
        listView = findViewById(R.id.list_view_Date);
        listView.setAdapter(foodAdapter);


        dateEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingDateView", dataSnapshot.toString());
                date.addFood(dataSnapshot);
                foodAdapter.notifyDataSetChanged();
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
        refDate.addChildEventListener(dateEventListener);
    }
}
