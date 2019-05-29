package com.example.android.fitdiary.Day.TrainingDay;

import com.example.android.fitdiary.Dao;
import com.example.android.fitdiary.Day.AddDayPresenter;
import com.example.android.fitdiary.Day.Day;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AddTrainingPresenter {
    private IView iview;
    private Dao dao;
    private FirebaseAuth mAuth;
    private List<Exercise> list;

    public AddTrainingPresenter(IView iview) {
        this.iview = iview;
        mAuth = FirebaseAuth.getInstance();
        dao = new Dao();
        list = new ArrayList<>();
    }

    public List<Exercise> getList() {
        return list;
    }

    public interface IView{

    }

}
