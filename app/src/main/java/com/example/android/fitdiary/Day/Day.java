package com.example.android.fitdiary.Day;

public class Day {
    private String date;

    public Day() {
    }

    public Day(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString(){
        return date;
    }

}
