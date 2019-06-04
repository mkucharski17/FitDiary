package com.example.android.fitdiary.Day.TrainingDay;

import com.example.android.fitdiary.Day.Day;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingDay extends Day {
   private List<Exercise> exercises;

    public TrainingDay() {
        super();
        exercises = new ArrayList<>();
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public TrainingDay(Date date) {
        super(date);
        exercises = new ArrayList<>();
    }

    public void deleteExercise(Exercise e){
        exercises.remove(e);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }


}
