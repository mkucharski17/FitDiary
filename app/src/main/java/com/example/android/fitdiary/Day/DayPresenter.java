package com.example.android.fitdiary.Day;


import com.example.android.fitdiary.Dao;
import com.google.firebase.auth.FirebaseAuth;

public class DayPresenter {
    protected Iview iview;
    protected Dao dao;
    protected FirebaseAuth mAuth;


    public DayPresenter() {
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
    }


    public interface Iview{

    }
}
