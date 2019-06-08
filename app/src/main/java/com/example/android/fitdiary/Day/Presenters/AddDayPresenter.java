package com.example.android.fitdiary.Day.Presenters;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Firebase.Dao;
import com.example.android.fitdiary.Day.DietDay.Models.DietDay;
import com.example.android.fitdiary.Day.Models.Day;
import com.example.android.fitdiary.Day.TrainingDay.Models.TrainingDay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

import static android.support.constraint.Constraints.TAG;

public class AddDayPresenter {
    private Day day;
    private IView iview;
    private Dao dao;
    private FirebaseAuth mAuth;
    private String type;


    public AddDayPresenter(IView iView, String type) {
        this.day = new Day();
        this.iview = iView;
        this.type = type;
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
    }

    public void createDate(Date date) {
        if (type.equals("diet"))
            day = new DietDay(date);
        else {
            day = new TrainingDay(date);
        }

    }

    public void save() {

        dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection(type + "days")
                .document(day.toString())
                .set(day)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }

    public Day getDay() {
        return day;
    }

    public interface IView {

    }
}

