package com.example.android.fitdiary.Day.TrainingDay;


import com.example.android.fitdiary.Day.AddDayPresenter;

public class TrainingDayPresenter extends AddDayPresenter {
    private TrainingDay trainingDay;

    public TrainingDayPresenter(iView iView) {
        super(iView);
        trainingDay = new TrainingDay();
    }


   public interface iView extends IView{

   }
}
