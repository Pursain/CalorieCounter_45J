package com.example.harrison.caloriecounter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference refFoodCalories;
    private DatabaseReference refUsers;
    private FirebaseDatabase database;
    private HashMap<String, HashMap<String, ArrayList<String>>> usersData;
    private HashMap<String, Integer> hmFoodData;
    private ArrayList<String> arrFoodData;
    private FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataStructures();
        createDatabaseListeners();
        initFoodAdapter(); //for testing purposes only, will not need in final code, firebase validator



        /*
        adding a food
        refFoodCalories.child("pears").setValue("15");

        removing a food
        refFoodCalories.child("pears").removeValue();

        */

    }

    private void initDataStructures() {
        usersData = new HashMap<>();
        hmFoodData = new HashMap<>();
        arrFoodData = new ArrayList<>();
    }

    public void createDatabaseListeners(){
        database = FirebaseDatabase.getInstance();
        refFoodCalories = database.getReference("foodCalories");
        refUsers = database.getReference("users");

        refUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("refUser", dataSnapshot.toString());
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

        refFoodCalories.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //IMPORTANT, ALWAYS ADD TO HASHMAP FIRST THEN ADD TO ADAPTER (BC IM A NOOB
                //AND CAN"T MAKE MY OWN ADAPTER
                hmFoodData.put(dataSnapshot.getKey(), Integer.valueOf(dataSnapshot.getValue(String.class)));
                foodAdapter.add(dataSnapshot.getKey());

                Log.d("refFoodCalories", dataSnapshot.toString());
                Log.d("idk", hmFoodData.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                hmFoodData.remove(dataSnapshot.getKey());
                foodAdapter.remove(dataSnapshot.getKey());

                Log.d("refFoodCalories", dataSnapshot.toString());
                Log.d("idk", hmFoodData.toString());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initFoodAdapter(){
        foodAdapter = new FoodAdapter(this, arrFoodData, hmFoodData);
        ListView results = findViewById(R.id.list_View);
        results.setAdapter(foodAdapter);
    }
}
//chdytrhfjmh