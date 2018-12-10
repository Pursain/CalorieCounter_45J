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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserView extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference refUser;
    private ArrayAdapter<String> dateAdapter;
    private ListView listViewDates;
    private User user;

    private String username;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        username = getIntent().getStringExtra("username");
        getIntent().removeExtra("username");
        this.setTitle(username);

        user = new User(username);

        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("users/" + username);

        dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getArrayOfDatesStrings());
        listViewDates = findViewById(R.id.listview_userMain);
        listViewDates.setAdapter(dateAdapter);

        listViewDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate = dateAdapter.getItem(i).split("\n")[0];
                Log.d("testingUserView", "clicked on " + selectedDate);
                onClickGoToDateView(view);
            }
        });

        refUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingUserView", "Added date " + dataSnapshot.getKey() +" to user " + username);
                user.addDate(dataSnapshot);     //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }
                dateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d("testingUserView", "Deleted date " + dataSnapshot.getKey() +" in user " + username);
                user.deleteDate(dataSnapshot);
                dateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClickAddDate(View view){
        Intent intent = new Intent(this, AddDateView.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onClickDeleteDate(View view){
        Intent intent = new Intent(this, DeleteDateView.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onClickGoToDateView(View view){
        Intent intent = new Intent(this, DateView.class);
        intent.putExtra("username", username);
        intent.putExtra("date", selectedDate);
        startActivity(intent);
    }

    public void onClickBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
