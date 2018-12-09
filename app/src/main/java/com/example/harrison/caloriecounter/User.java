package com.example.harrison.caloriecounter;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class User {
    String username;
    ArrayList<Date> arrayOfDates;
    ArrayList<String> arrayOfDatesStrings;

    public User(String username) {
        this.username = username;
        arrayOfDates = new ArrayList();
        arrayOfDatesStrings = new ArrayList();
    }

//    public User(String username, DataSnapshot dataSnapshot){
//        this.username = username;
//        arrayOfDates = new ArrayList();
//        arrayOfDatesStrings = new ArrayList();
//
//        for (DataSnapshot ds: dataSnapshot.getChildren()){
//            Date newDate = new Date(ds.getKey(), ds);
//            arrayOfDates.add(newDate);
//            arrayOfDatesStrings.add(newDate.toString());
//        }
//    }

    public void addDate(DataSnapshot dataSnapshot){
        //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }

        Date newDate = new Date(dataSnapshot.getKey(), dataSnapshot);
        arrayOfDates.add(newDate);
        arrayOfDatesStrings.add(newDate.toString());
    }

    public ArrayList<String> getArrayOfDatesStrings() {
        return arrayOfDatesStrings;
    }

    public String getUsername(){
        return username;
    }

    public String toString(){
        return username;
    }
}
