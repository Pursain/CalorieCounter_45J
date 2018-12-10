package com.example.harrison.caloriecounter;
/*
This group consists of the following people

Crystal Lai Ton Nu  17316217
Ashley Ju           89693512
Katrin Martinez     38176706
Harrison Huang      48425701

*/

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Date {
    private String date;
    private int totalCalories;

    ArrayList<Food> arrayOfFoods;
    ArrayList<String> arrayOfFoodsStrings;
    ArrayList<String> arrayOfJustFoods;

    public Date(String date){
        this.date = date;
        arrayOfFoods = new ArrayList<>();
        arrayOfFoodsStrings = new ArrayList<>();
        arrayOfJustFoods = new ArrayList<>();
    }

    public Date(String date, DataSnapshot dataSnapshot){
        this.date = date;
        arrayOfFoods = new ArrayList<>();
        arrayOfFoodsStrings = new ArrayList<>();
        arrayOfJustFoods = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Food newFood = new Food(ds.getKey(), ds);
            arrayOfFoods.add(newFood);
            arrayOfFoodsStrings.add(newFood.toString());
            arrayOfJustFoods.add(ds.getKey());
        }
        updateTotalCalories();
    }

    public void addFood(DataSnapshot dataSnapshot){
        //{ key = banana, value = {quanity=2, calories=40} }
        Food newFood = new Food(dataSnapshot.getKey(), dataSnapshot);
        arrayOfFoods.add(newFood);
        arrayOfFoodsStrings.add(newFood.toString());
        arrayOfJustFoods.add(dataSnapshot.getKey());
        updateTotalCalories();
    }

    public ArrayList<String> getArrayOfFoodsStrings() {
        return arrayOfFoodsStrings;
    }

    public ArrayList<String> getArrayOfJustFoods() {
        return arrayOfJustFoods;
    }

    public ArrayList<Food> getArrayOfFoods() {
        return arrayOfFoods;
    }

    private void updateTotalCalories(){
        totalCalories = 0;
        for(Food f : arrayOfFoods){
            totalCalories += f.getTotalCalories();
        }
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public String getDate(){
        return date;
    }

    public String toString(){
        return date + "\n" + String.valueOf(totalCalories) + " Total Calories";
    }
}
