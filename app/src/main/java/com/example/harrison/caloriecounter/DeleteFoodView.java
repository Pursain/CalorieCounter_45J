package com.example.harrison.caloriecounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DeleteFoodView extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference refDate;
    private ChildEventListener dateEventListener;
    private ArrayAdapter<String> foodAdapter;
    private ListView listView;
    private TextView textView_confirmDelete;
    private Date date;

    private String username;
    private String dateString;
    private String selectedFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_food);

        username = getIntent().getStringExtra("username");
        dateString = getIntent().getStringExtra("date");
        getIntent().removeExtra("username");
        getIntent().removeExtra("date");

        date = new Date(dateString);

        database = FirebaseDatabase.getInstance();
        refDate = database.getReference("users/" + username + "/" + dateString);

        foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView = findViewById(R.id.listView_removeFood);
        listView.setAdapter(foodAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedFood = foodAdapter.getItem(i);
                Log.d("TestingFoodDelSearch", "clicked on " + selectedFood);
                textView_confirmDelete = findViewById(R.id.choiceText);
                textView_confirmDelete.setText(selectedFood);
            }
        });

        dateEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingDateView", dataSnapshot.toString());
                date.addFood(dataSnapshot);
                foodAdapter.add(dataSnapshot.getKey());
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

    public void deleteButtonAction(View view){
        refDate.child(selectedFood).removeValue();
        goBack(view);
    }

    public void searchButton(View view){
        foodAdapter.clear();
        String searchText = ((EditText)findViewById(R.id.searchEditText)).getText().toString();
        Log.d("TestingFoodDelSearch", "search terms  " + searchText);
        for (String fName : date.getArrayOfJustFoods()){
            Log.d("TestingFoodDelSearch", "looping " + fName);
            if (fName.substring(0, searchText.length()).equalsIgnoreCase(searchText)){
                Log.d("TestingFoodDelSearch", "found match " + fName);
                foodAdapter.add(fName);
            }
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(this, DateView.class);
        intent.putExtra("username", username);
        intent.putExtra("date", dateString);
        startActivity( intent);
    }
}
