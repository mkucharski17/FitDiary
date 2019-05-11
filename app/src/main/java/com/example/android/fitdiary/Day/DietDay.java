package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Food;

import java.util.ArrayList;

public class DietDay extends Day {
    ArrayList<Food> food;

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public DietDay(String date) {
        super(date);
        this.food = new ArrayList<>();
    }

    public void addFood(Food food){
        this.food.add(food);
    }
}
