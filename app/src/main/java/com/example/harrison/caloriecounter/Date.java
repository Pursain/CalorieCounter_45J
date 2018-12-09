package com.example.harrison.caloriecounter;

import java.util.ArrayList;

public class Date {
    int day;
    int month;
    int year;
    int totalCalories;
    ArrayList<Food> arrayOfFoods;

    //useful for using an ArrayAdapter since android has a default arrayList adapter
    //use it as the display
    ArrayList<String> arrayOfFoodsStrings;

    public Date(String formatedDate){
        //TODO format check?

        /*
        the string format is "month-day-year" eg. "12-20-18"
         */

        String[] splitDate = formatedDate.split("-");
        month = Integer.getInteger(splitDate[0]);
        day = Integer.getInteger(splitDate[1]);
        year = Integer.getInteger(splitDate[2]);

        arrayOfFoods = new ArrayList();
        arrayOfFoodsStrings = new ArrayList();
        totalCalories = 0;
    }

    public Date(String formatedDate, ArrayList<Food> arrayOfFoods){
        //TODO format check?

        /*
        the string format is "month-day-year" eg. "12-20-18"
         */

        String[] splitDate = formatedDate.split("-");
        month = Integer.getInteger(splitDate[0]);
        day = Integer.getInteger(splitDate[1]);
        year = Integer.getInteger(splitDate[2]);

        this.arrayOfFoods = new ArrayList();
        arrayOfFoodsStrings = new ArrayList();
        totalCalories = 0;

        this.arrayOfFoods = arrayOfFoods;
        updateArrayOfFoodsStrings();
        updateTotalCalories();
    }

    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;

        arrayOfFoods = new ArrayList();
        arrayOfFoodsStrings = new ArrayList();
        totalCalories = 0;
    }

    public Date(int month, int day, int year, ArrayList<Food> arrayOfFoods){
        this.month = month;
        this.day = day;
        this.year = year;
        this.arrayOfFoods = arrayOfFoods;
        updateArrayOfFoodsStrings();

        this.arrayOfFoods = new ArrayList();
        arrayOfFoodsStrings = new ArrayList();
        totalCalories = 0;

        this.arrayOfFoods = arrayOfFoods;
        updateArrayOfFoodsStrings();
        updateTotalCalories();
    }

    public int getDay() {
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public ArrayList<Food> getArrayOfFoods() {
        return arrayOfFoods;
    }

    public ArrayList<String> getArrayOfFoodStrings() {
        return arrayOfFoodsStrings;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    //idk when you'll need it but it's here
    public Food getFoodAt(int index){
        return arrayOfFoods.get(index);
    }

    //I don't think we need this but I already wrote it...
    //It's because we make data changes only through the Add/Delete Activities
    //and whenever you switch activities (back to the view), all the local data is
    //rebuilt from firebase thus we should only make edits to the firebase data
    public void deleteFood(String foodName){
        //loop through, if name matches, remove it
        for (int i = 0; i < arrayOfFoods.size(); ++i){
            if (arrayOfFoods.get(i).getName() == foodName)
                arrayOfFoods.remove(i);
        }
        updateTotalCalories();
    }

    //I don't think we need this but I already wrote it...
    //It's because we make data changes only through the Add/Delete Activities
    //and whenever you switch activities (back to the view), all the local data is
    //rebuilt from firebase thus we should only make edits to the firebase data
    public void addFood(String name, int quanity, int calories) {
        arrayOfFoods.add(new Food(name, quanity, calories));
        updateTotalCalories();
    }

    public String toString(){
        //used by the userView, not the DateView
        //the user.class will be similar function to updateArrayOfFoodsStrings()
        return String.valueOf(month) + "-" + String.valueOf(day) + "-" + String.valueOf(year) + ":\t"
                + String.valueOf(totalCalories) + " total calories";
    }

    private void updateTotalCalories(){
        totalCalories = 0;
        for(int i = 0; i < arrayOfFoods.size(); ++i){
            totalCalories += arrayOfFoods.get(i).getCalories() * arrayOfFoods.get(i).getQuanity();
        }
    }

    private void updateArrayOfFoodsStrings(){
        arrayOfFoodsStrings.clear();
        for (Food food: arrayOfFoods){
            arrayOfFoodsStrings.add(food.toString());
            //food.toString() should be formatted so like it contains the food, quanity, and calorie info
        }
    }
}
