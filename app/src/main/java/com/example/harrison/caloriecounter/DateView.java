package com.example.harrison.caloriecounter;
/*
This group consists of the following people

Crystal Lai Ton Nu  17316217
Ashley Ju           89693512
Katrin Martinez     38176706
Harrison Huang      48425701

*/
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView textView_totalCalories;

    private String username;
    private String dateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_view);

        username = getIntent().getStringExtra("username");
        getIntent().removeExtra("username");

        dateString = getIntent().getStringExtra("date");
        getIntent().removeExtra("date");

        this.setTitle(dateString);   //set as title

        date = new Date(dateString);

        textView_totalCalories = findViewById(R.id.DatetextView);
        database = FirebaseDatabase.getInstance();
        refDate = database.getReference("users/" + username +"/" + dateString);

        foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, date.getArrayOfFoodsStrings());
        listView = findViewById(R.id.foodList);
        listView.setAdapter(foodAdapter);


        dateEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingDateView", dataSnapshot.toString());
                date.addFood(dataSnapshot);
                foodAdapter.notifyDataSetChanged();

                textView_totalCalories.setText(Integer.toString(date.getTotalCalories()) + " Total Calories");
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

    public void moveToDeleteFoodView(View view){
        Intent newIntent = new Intent(this, DeleteFoodView.class);
        newIntent.putExtra("username", username);
        newIntent.putExtra("date", dateString);
        startActivity(newIntent);
    }

    public void addFoodItems(View view){
        Intent newIntent = new Intent(this, AddFood.class);
        newIntent.putExtra("username", username);
        newIntent.putExtra("date", dateString);
        startActivity(newIntent);
    }

    public void goBack(View view){
        Intent newIntent = new Intent(this, UserView.class);
        newIntent.putExtra("username", username);
        startActivity(newIntent);
    }
}
