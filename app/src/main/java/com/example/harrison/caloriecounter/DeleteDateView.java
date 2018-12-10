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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteDateView extends AppCompatActivity
{
    private FirebaseDatabase database;
    private DatabaseReference refUser;
    private ArrayAdapter<String> dateAdapter;
    private ListView listViewDates;
    private TextView textView_confirm;
    private User user;

    private String username;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_date);

        username = getIntent().getStringExtra("username");
        getIntent().removeExtra("username");
        this.setTitle(username);

        user = new User(username);

        database = FirebaseDatabase.getInstance();
        refUser = database.getReference("users/" + username);
        selectedDate = "";

        dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user.getArrayOfJustDates());
        listViewDates = findViewById(R.id.listView_deleteDate);
        listViewDates.setAdapter(dateAdapter);

        listViewDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate = dateAdapter.getItem(i);
                Log.d("testingDeleteDateView", "clicked on " + selectedDate);
                textView_confirm = findViewById(R.id.textView_deleteDateConfirm);
                textView_confirm.setText(selectedDate);
            }
        });

        refUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("testingDeleteDateView", "Added date " + dataSnapshot.getKey() +" to user " + username);
                user.addDate(dataSnapshot);     //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }
                dateAdapter.add(dataSnapshot.getKey());
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

    public void onClickDeleteDate(View view){
        if (!selectedDate.equals("")) {
            refUser.child(selectedDate).removeValue();
            Toast.makeText(this, "You have deleted " + selectedDate + " from " + username, Toast.LENGTH_LONG).show();
            goBack(view);
        }
        else
        {
            Toast.makeText(this, "Please select a date to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void searchButton(View view){
        Log.d("TestingLooop", user.getArrayOfJustDates().toString());
        dateAdapter.clear();
        Log.d("TestingLooop", user.getArrayOfJustDates().toString());
        String searchText = ((EditText)findViewById(R.id.editText_deleteDateSearch)).getText().toString();
        for (String date : user.getArrayOfJustDates()){
            //Log.d("TestingLooop", date);
            if (searchText.length() <= date.length() && date.substring(0, searchText.length()).equalsIgnoreCase(searchText)){
                dateAdapter.add(date);
            }
        }
    }
//
//    public void refresh(String update) {
//        listAdapter.clear();
//        for (Date d : dateList) {
//            if (d.getDate().equalsIgnoreCase(update)) {
//                listAdapter.add(d.getDate());
//            }
//        }
//    }
//
//    public void search(View view)
//    {
//        EditText editSearch = findViewById(R.id.editText_deleteDateSearch);
//        String search = editSearch.getText().toString();
//
//        for (Date d: dateList)
//        {
//            if (d.getDate().equals(search) || d.getDate().contains(search))
//            {
//                listAdapter.add(d.getDate());
//            }
//            else
//            {
//                Toast.makeText(this, search + " not found.", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public void deleteDate(View view)
//    {
//        ListView results = findViewById(R.id.listView_deleteDate);
//
//        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Date selectedItem = (Date) parent.getItemAtPosition(position);
//                refTesting.child(selectedItem.getDate()).removeValue();
//                dateList.remove(selectedItem);
//                refresh(selectedItem.getDate());
//            }
//        });
//    }

    public void goBack(View view)
    {
        Intent intent = new Intent(this, UserView.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}