package com.example.android.fitdiary;

import com.example.android.fitdiary.Exercise;

import java.util.List;

public class Training {

private String name;
private List<Exercise> Exercises;

    public Training(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return Exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        Exercises = exercises;
    }
}
