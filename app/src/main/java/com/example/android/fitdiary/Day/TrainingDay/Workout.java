package com.example.android.fitdiary.Day.TrainingDay;

import java.util.List;

public class Workout {
    private List<Exercise> exercises;

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
