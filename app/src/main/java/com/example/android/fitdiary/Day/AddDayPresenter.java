package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Day.Day;

public class AddDayPresenter {
    private Day day;
    private IView iView;

    public AddDayPresenter(IView iView){
        this.day = new Day();
        this.iView = iView;
    }

    public void updateDate(String date){
        day.setDate(date);
    }

    public Day getDay() {
        return day;
    }

    public interface IView{

    }
}
