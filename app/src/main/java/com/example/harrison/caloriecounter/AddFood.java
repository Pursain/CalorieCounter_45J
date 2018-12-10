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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFood extends AppCompatActivity {

    private Date date;
    private FirebaseDatabase database;
    private DatabaseReference refDate;
    private ChildEventListener dateEventListener;


    private String username;
    private String dateString;

    private EditText editFood;
    private EditText editQuantity;
    private EditText editCalories;
    private String foodName;
    private int quantity;
    private int calories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        username = getIntent().getStringExtra("username");
        dateString = getIntent().getStringExtra("date");

        date = new Date(dateString);

        database = FirebaseDatabase.getInstance();
        refDate = database.getReference("users/"+ username +"/" + dateString);

        dateEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingDateView", dataSnapshot.toString());
                date.addFood(dataSnapshot);
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

    public void addFoodItem(View view)
    {

        editFood = findViewById(R.id.nameEditText);
        foodName = editFood.getText().toString();

        editQuantity = findViewById(R.id.quantityEditText);
        if (editQuantity.getText().toString().equals(""))
            quantity = 0;
        else
            quantity = Integer.valueOf(editQuantity.getText().toString());

        editCalories = findViewById(R.id.caloriesEditText);

        if (editCalories.getText().toString().equals(""))
            calories = 0;
        else
            calories = Integer.valueOf(editCalories.getText().toString());

        Log.d("Testing add food", foodName + " " + editQuantity.getText().toString() + " " + editCalories.getText().toString());

        if(foodName.length() > 0 && quantity > 0 && calories > 0)
        {
            Log.d("Testing add      food", date.getArrayOfJustFoods().toString());
            if (date.getArrayOfJustFoods().contains(foodName)){
                Log.d("Testingaddfood", "found in exisitng");
                for (Food f: date.getArrayOfFoods()){
                    Log.d("Testingaddfoodssss", f.getName());
                    if (f.getName().equals(foodName)){
                        refDate.child(foodName).child("calories").setValue(calories);
                        refDate.child(foodName).child("quantity").setValue(f.getQuantity() + quantity);
                        Toast.makeText(AddFood.this, foodName + " already exists! Quantity and Calories has been updated.", Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
            else
            {
                refDate.child(foodName).child("calories").setValue(calories);
                refDate.child(foodName).child("quantity").setValue(quantity);
                Toast.makeText(AddFood.this, foodName + " has been successfully added.", Toast.LENGTH_LONG).show();
            }
            goBack(view);
        }else {
            editFood.setText("");
            editQuantity.setText("");
            editCalories.setText("");
            Toast.makeText(AddFood.this, "Invalid entry, try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, DateView.class);
        intent.putExtra("username", username);
        intent.putExtra("date", dateString);
        startActivity(intent);
    }
}
