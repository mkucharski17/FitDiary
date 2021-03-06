package com.example.android.fitdiary.Day.TrainingDay.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;


/*
* Exercise - class represents one exercise form workout
* */
public class Exercise implements Serializable {
    private String name;
    private int sets;
    private int weight;
    private int reps;

    public Exercise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    /*
    * to String method for arrayAdapter
    * */
    @NonNull
    public String toString() {
        return (name + "  " + sets + "x" + reps + "  " + weight + "kg");

    }
}
