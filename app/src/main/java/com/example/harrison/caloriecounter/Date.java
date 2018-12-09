package com.example.harrison.caloriecounter;


import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Date {
    private String date;
    private int totalCalories;

    ArrayList<Food> arrayOfFoods;
    ArrayList<String> arrayOfFoodsStrings;

    public Date(String date){
        this.date = date;
        arrayOfFoods = new ArrayList<>();
        arrayOfFoodsStrings = new ArrayList<>();
    }

    public Date(String date, DataSnapshot dataSnapshot){
        this.date = date;
        arrayOfFoods = new ArrayList<>();
        arrayOfFoodsStrings = new ArrayList<>();

        for (DataSnapshot ds : dataSnapshot.getChildren()){
            Food newFood = new Food(ds.getKey(), ds);
            arrayOfFoods.add(newFood);
            arrayOfFoodsStrings.add(newFood.toString());
        }
        updateTotalCalories();
    }

    public void addFood(DataSnapshot dataSnapshot){
        //{ key = banana, value = {quantity=2, calories=40} }
        Food newFood = new Food(dataSnapshot.getKey(), dataSnapshot);
        arrayOfFoods.add(newFood);
        arrayOfFoodsStrings.add(newFood.toString());
        updateTotalCalories();
    }

    public ArrayList<String> getArrayOfFoodsStrings() {
        return arrayOfFoodsStrings;
    }

    private void updateTotalCalories(){
        totalCalories = 0;
        for(Food f : arrayOfFoods){
            totalCalories += f.getTotalCalories();
        }
    }

    public String getDate(){
        return date;
    }

    public String toString(){
        return date + "\n" + String.valueOf(totalCalories) + " total calories";
    }
}
