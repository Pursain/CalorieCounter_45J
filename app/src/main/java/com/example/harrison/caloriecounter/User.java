package com.example.harrison.caloriecounter;

import java.util.ArrayList;

public class User {
    String username;
    ArrayList<Date> arrayOfDates;
    ArrayList<String> arrayOfDatesStrings;

    public User(String username){
        this.username = username;
        arrayOfDates = new ArrayList();
        arrayOfDatesStrings = new ArrayList();
    }

    public User(String username, ArrayList<Date>)

    public ArrayList<Date> getArrayOfDates() {
        return arrayOfDates;
    }

    public ArrayList<String> getArrayOfDatesStrings() {
        return arrayOfDatesStrings;
    }

    public String getUsername(){
        return username;
    }
}
