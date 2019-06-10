package com.example.android.fitdiary.Day.TrainingDay.Presenters;

import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;

import java.util.ArrayList;
import java.util.List;

/*
* AddExercisePresenter - presenter for AddExercise class
* */

public class AddExercisePresenter {
    private IView iview;
    private List<Exercise> allExercisesList;

    public AddExercisePresenter(IView iview, List<Exercise> list) {
        this.iview = iview;
        allExercisesList = new ArrayList<>();
        if (list != null)
            allExercisesList.addAll(list);
    }

    public List<Exercise> getAllExercisesList() {
        return allExercisesList;
    }

    public interface IView {
        void hideDeleteButton();
    }

}
