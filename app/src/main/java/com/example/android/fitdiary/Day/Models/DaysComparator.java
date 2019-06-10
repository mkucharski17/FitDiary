package com.example.android.fitdiary.Day.Models;

import com.example.android.fitdiary.Day.Models.Day;

import java.util.Comparator;


/*
* class created to compare Days considered date
* */
public class DaysComparator implements Comparator<Day> {

    @Override
    public int compare(Day day1, Day day2) {
        return day1.getDate().compareTo(day2.getDate());
    }
}

