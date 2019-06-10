package com.example.android.fitdiary.Day.TrainingDay.Models;

import com.example.android.fitdiary.Day.Models.Day;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* TrainingDay - class represents one training day, extends day by List of exercises
* */
public class TrainingDay extends Day {
    private List<Exercise> exercises;

    public TrainingDay() {
        super();
        exercises = new ArrayList<>();
    }


    public TrainingDay(Date date) {
        super(date);
        exercises = new ArrayList<>();
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }


}
