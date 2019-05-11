package com.example.android.fitdiary.Day;


public class AddTrainingDayPresenter extends AddDayPresenter {
    private TrainingDay trainingDay;

    public AddTrainingDayPresenter(iView iView) {
        super(iView);
        trainingDay = new TrainingDay();
    }


   public interface iView extends IView{

   }
}
