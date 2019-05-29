package com.example.android.fitdiary.Day;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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



    public String toString(){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
        return  formatter.format(date);

    }

}
