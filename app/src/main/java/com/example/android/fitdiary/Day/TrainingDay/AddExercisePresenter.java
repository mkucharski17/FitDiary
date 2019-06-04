package com.example.android.fitdiary.Day.TrainingDay;

import com.example.android.fitdiary.Dao;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AddExercisePresenter {
    private IView iview;
    private List<Exercise> allExercisesList;

    public AddExercisePresenter(IView iview, List<Exercise> list ) {
        this.iview = iview;
        allExercisesList = new ArrayList<>();
        if(list != null)
            allExercisesList.addAll(list);
    }

    public List<Exercise> getAllExercisesList() {
        return allExercisesList;
    }

    public interface IView{
        void hideDeleteButton();
    }

}
