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

public class AddDateView extends AppCompatActivity
{
    private FirebaseDatabase database;
    private DatabaseReference refUser;
    private User user;
    private String username;

    private EditText editTextDateInput;
    private String inputedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        username = getIntent().getStringExtra("username");
        getIntent().removeExtra("username");
        this.setTitle(username);

        user = new User(username);

        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("users/" + username);

        refUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingUserViewAdd", "Added date " + dataSnapshot.getKey() +" to user " + username);
                user.addDate(dataSnapshot);     //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }
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

    public void addDate(View view)
    {
        editTextDateInput = findViewById(R.id.editText_addDate);
        inputedDate = editTextDateInput.getText().toString();
        String[] stringArr = inputedDate.split("-");
        if (stringArr.length != 3 || !stringArr[0].matches("[0-9]+")
                || !stringArr[1].matches("[0-9]+") || !stringArr[2].matches("[0-9]+")
                || Integer.valueOf(stringArr[0]) > 12 || Integer.valueOf(stringArr[1]) > 31
                || Integer.valueOf(stringArr[2]) > 99) {
            editTextDateInput.setText("");
            Toast.makeText(this, "Oof! This date is invalid, the format is MM-DD-YY", Toast.LENGTH_SHORT).show();
        }else if (user.getArrayOfJustDates().contains(inputedDate)){
            editTextDateInput.setText("");
            Toast.makeText(this, "Oops! This date is already in your list, try another date!", Toast.LENGTH_SHORT).show();
        }else {
            refUser.child(inputedDate).setValue("");
            Toast.makeText(this, "Great! You have added a date. Click on the date in the list to add some foods!", Toast.LENGTH_LONG).show();
            onClickGoBack(view);
        }
    }

    public void onClickGoBack(View view)
    {
        Intent intent = new Intent(this, UserView.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}