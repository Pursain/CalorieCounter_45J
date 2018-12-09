package com.example.harrison.caloriecounter;

import com.google.firebase.database.DataSnapshot;

public class Food {
    private String name;
    private int quanity;
    private int calories;
    private int totalCalories;

    public Food(String name, DataSnapshot dataSnapshot){
        this.name = name;
       // Log.d("testing Foodzzz.java", name + " " + dataSnapshot.getKey());
        for (DataSnapshot ds: dataSnapshot.getChildren()){
           // Log.d("testing Food.java", name + "current key" + ds.getKey());
            if(ds.getKey().equals("quanity")) {
                this.quanity = ds.getValue(Integer.class);
               // Log.d("testing Food.java", name + "quanity" + Integer.toString(this.quanity));
            }
            if(ds.getKey().equals("calories")) {
                this.calories = ds.getValue(Integer.class);
                //Log.d("testing Food.java", name + "calories" + Integer.toString(this.quanity));
            }
        }
        totalCalories = this.quanity * this.calories;
    }

    public void setQuanity(int quanity){
        this.quanity = quanity;
    }

    public int getQuanity(){
        return quanity;
    }

    public int getTotalCalories(){
        return totalCalories;
    }

    public String toString(){
        return name + "\nQuanity " + quanity + "\nCalories " + calories;
    }
}
