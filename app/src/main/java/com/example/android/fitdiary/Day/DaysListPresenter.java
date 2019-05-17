package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Saver;

import java.util.ArrayList;


public class DaysListPresenter {
    private ArrayList<Day> DaysList;
    private IView iview;
    private Saver dao;

    public DaysListPresenter(IView iview) {
        DaysList = new ArrayList<>();
        this.iview = iview;
    }
    public ArrayList<Day> getDaysList() {
        return DaysList;
    }

    public void addDay(String date){
        Day day = new Day(date);
        DaysList.add(day);
    }

    public interface IView{
        void hideButton();
        void showButton();
    }
}
