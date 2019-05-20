package com.example.android.fitdiary.Day;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Day {
    private Date date;

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
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        return s;
    }

}
