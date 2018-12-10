package com.example.harrison.caloriecounter;
/*
This group consists of the following people

Crystal Lai Ton Nu  17316217
Ashley Ju           89693512
Katrin Martinez     38176706
Harrison Huang      48425701

*/
import com.google.firebase.database.DataSnapshot;

public class Food {
    private String name;
    private int quantity;
    private int calories;
    private int totalCalories;

    public Food(String name, DataSnapshot dataSnapshot){
        this.name = name;
       // Log.d("testing Foodzzz.java", name + " " + dataSnapshot.getKey());
        for (DataSnapshot ds: dataSnapshot.getChildren()){
           // Log.d("testing Food.java", name + "current key" + ds.getKey());
            if(ds.getKey().equals("quantity")) {
                this.quantity = ds.getValue(Integer.class);
               // Log.d("testing Food.java", name + "quantity" + Integer.toString(this.quantity));
            }
            if(ds.getKey().equals("calories")) {
                this.calories = ds.getValue(Integer.class);
                //Log.d("testing Food.java", name + "calories" + Integer.toString(this.quantity));
            }
        }
        totalCalories = this.quantity * this.calories;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getTotalCalories(){
        return totalCalories;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name + "\nQuantity " + quantity + "\nCalories " + calories;
    }
}
