package com.example.android.fitdiary.Workout;

import java.util.List;

public class Workout {
    List<Exercise> exercises;

    public Workout(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Workout() {
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
