package com.example.android.fitdiary;

import com.example.android.fitdiary.Day.Day;

import java.util.Comparator;

public class DaysComparator implements Comparator<Day>  {

        @Override
        public int compare(Day day1 ,Day day2) {
            return day1.getDate().compareTo(day2.getDate());
        }
    }

