package com.example.android.fitdiary;

import com.example.android.fitdiary.Exercise;

import java.util.List;

public class Training {

private List<Exercise> Exercises;

    public Training(List<Exercise> exercises) {
        Exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return Exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        Exercises = exercises;
    }
}
