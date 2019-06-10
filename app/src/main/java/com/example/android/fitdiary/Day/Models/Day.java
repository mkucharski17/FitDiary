package com.example.android.fitdiary.Day.Models;

import android.support.annotation.NonNull;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/*
* base Day class  represents day consists of date
* */

public class Day {
    protected Date date;


    public Day() {
    }

    public Day(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    /*
    * toString method using by arrayAdapter
    * */
    @NonNull
    public String toString() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
        return formatter.format(date);
    }

}
