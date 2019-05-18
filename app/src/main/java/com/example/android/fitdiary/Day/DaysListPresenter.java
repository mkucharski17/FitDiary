package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Day.DietDay.DietDay;
import com.example.android.fitdiary.Day.TrainingDay.TrainingDay;

import java.util.ArrayList;


public class DaysListPresenter {
    private ArrayList<Day> DaysList;
    private IView iview;
    private String type;

    public DaysListPresenter(IView iview, String type) {
        DaysList = new ArrayList<>();
        this.type = type;
        this.iview = iview;
    }
    public ArrayList<Day> getDaysList() {
        return DaysList;
    }

    public void addDay(String date){
        Day day;
       if(type.equals("diet"))
         day = new DietDay(date);
       else
           day = new TrainingDay(date);
        DaysList.add(day);
    }

    public interface IView{
        void hideButton();
        void showButton();
    }
}
