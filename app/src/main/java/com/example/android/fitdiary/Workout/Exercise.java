package com.example.android.fitdiary.Workout;

import java.util.List;

public class Exercise {
    List<Set> sets;

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public Exercise(List<Set> sets) {
        this.sets = sets;
    }
}
