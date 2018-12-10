package com.example.harrison.caloriecounter;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class User {
    String username;
    ArrayList<Date> arrayOfDates;
    ArrayList<String> arrayOfDatesStrings;
    ArrayList<String> arrayOfJustDates;

    public User(String username) {
        this.username = username;
        arrayOfDates = new ArrayList();
        arrayOfDatesStrings = new ArrayList();
        arrayOfJustDates = new ArrayList();
    }

    public void addDate(DataSnapshot dataSnapshot){
        //{ key = 12-1-18, value = {apple={quanity=4, calories=30}} }

        Date newDate = new Date(dataSnapshot.getKey(), dataSnapshot);
        arrayOfDates.add(newDate);
        arrayOfDatesStrings.add(newDate.toString());
        arrayOfJustDates.add(newDate.getDate());
    }

    public void deleteDate(DataSnapshot dataSnapshot){
        Date newDate = new Date(dataSnapshot.getKey(), dataSnapshot);
        arrayOfDates.remove(newDate);
        arrayOfDatesStrings.remove(newDate.toString());
        arrayOfJustDates.remove(newDate.getDate());
    }

    public ArrayList<String> getArrayOfDatesStrings() {
        return arrayOfDatesStrings;
    }

    public ArrayList<Date> getArrayOfDates() {
        return arrayOfDates;
    }

    public ArrayList<String> getArrayOfJustDates() {
        return new ArrayList<>(arrayOfJustDates);
    }

    public String getUsername(){
        return username;
    }

    public String toString(){
        return username;
    }
}
