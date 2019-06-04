package com.example.android.fitdiary.Day.DietDay;

import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.TrainingDay.Exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DietDay extends Day {
    private List<Food> food;

    public DietDay() {
        super();
        food = new ArrayList<>();
    }


    public DietDay(Date date) {
        super(date);
        food = new ArrayList<>();
    }

    public List<Food> getFood() {
        return food;
    }

    public void addFood(Food f) {
        this.food.add(f);
    }
}
