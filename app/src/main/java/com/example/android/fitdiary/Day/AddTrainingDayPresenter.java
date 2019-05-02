package com.example.android.fitdiary.Day;

import com.example.android.fitdiary.Workout.Workout;

public class AddTrainingDayPresenter extends AddDayPresenter {
    private TrainingDay trainingDay;

    public AddTrainingDayPresenter(iView iView) {
        super(iView);
        trainingDay = new TrainingDay();
    }

    public void addTrainingDay(Workout workout){
        trainingDay.addWorkout(workout);
    }

   public interface iView extends IView{

   }
}
