package com.example.android.fitdiary.Presenters;



import com.example.android.fitdiary.Firebase.Dao;
import com.google.firebase.auth.FirebaseAuth;

/*
 * Base class for number of presenters
 * */

public abstract class BasePresenter {
    protected Iview iview;
    protected Dao dao;
    protected FirebaseAuth mAuth;


    /*
     * initializing database object and Authentication reference
     * */
    public BasePresenter() {
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
    }


    public interface Iview {

        void loadAdapter();

        void setListeners();

    }
}
