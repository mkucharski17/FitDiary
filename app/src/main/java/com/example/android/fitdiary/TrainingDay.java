package com.example.android.fitdiary;

import java.time.LocalDate;
import java.util.List;

public class TrainingDay {
    private String date;
    private List<Training> workouts;

    public List<Training> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Training> workouts) {
        this.workouts = workouts;
    }

    public TrainingDay(String date, List<Training> workouts) {
        this.date = date;
        this.workouts = workouts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
