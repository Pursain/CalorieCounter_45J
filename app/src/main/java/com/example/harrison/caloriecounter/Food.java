package com.example.ashleyju.caloriecounter4;

public class Food
{

    private String foodName;
    private int calories;
    private int quantity;

    public Food()
    {
        foodName = "";
        calories = 0;
        quantity = 0;
    }

    public Food(String food, int quantity, int calories)
    {
        this.foodName = food;
        this.quantity = quantity;
        this.calories = calories;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public int getCalories()
    {
        return calories;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String toString()
    {
        return foodName + " " + quantity + " " + calories;
    }



}
