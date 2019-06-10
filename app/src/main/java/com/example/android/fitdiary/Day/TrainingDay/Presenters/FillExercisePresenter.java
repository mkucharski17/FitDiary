package com.example.android.fitdiary.Day.TrainingDay.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Firebase.Dao;
import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.Presenters.BasePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.constraint.Constraints.TAG;


/*
*
* FillExercisePresenter - class that mans FillExerciseActivity
* */
public class FillExercisePresenter extends BasePresenter {
    private Exercise exercise;
    private boolean New;
    private IView iView;

    public FillExercisePresenter(IView iView) {
        super();
        this.iView = iView;
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public boolean isNew() {
        return New;
    }

    public void setNew(boolean aNew) {
        New = aNew;
    }


    /*
    * adding new created exercise to list of exercises
    * */
    public void saveNewExercise() {
        dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("exercises")
                .document(exercise.getName())
                .set(exercise)
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


    public interface IView {

    }
}
