package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Workout.Workout;

import java.util.ArrayList;
import java.util.List;

public class TrainingDay extends Day {
   private List<Workout> workouts;

    public TrainingDay() {
        super();
        workouts = new ArrayList<>();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        this.workouts.add(workout);
    }

    public TrainingDay(String date) {
        super(date);
        workouts = new ArrayList<>();
    }

    public void addTraining(Workout workout){
        workouts.add(workout);
    }

}
