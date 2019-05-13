package com.example.android.fitdiary.Day.DietDay;

import com.example.android.fitdiary.Day.Day;

import java.util.ArrayList;

public class DietDay extends Day {
    private ArrayList<Food> food;

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
