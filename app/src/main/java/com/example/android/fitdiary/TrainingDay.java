package com.example.android.fitdiary;

import java.time.LocalDate;
import java.util.List;

public class TrainingDay {
    private LocalDate date;
    private List<Training> workouts;

    public List<Training> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Training> workouts) {
        this.workouts = workouts;
    }

    public TrainingDay(LocalDate date, List<Training> workouts) {
        this.date = date;
        this.workouts = workouts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
