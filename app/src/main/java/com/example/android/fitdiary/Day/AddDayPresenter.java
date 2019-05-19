package com.example.android.fitdiary.Day;


import java.util.Date;

public class AddDayPresenter {
    private Day day;
    private IView iView;

    public AddDayPresenter(IView iView) {
        this.day = new Day();
        this.iView = iView;
    }

    public void updateDate(Date date) {
        day.setDate(date);
    }

    public Day getDay() {
        return day;
    }

    public interface IView {

    }
}

