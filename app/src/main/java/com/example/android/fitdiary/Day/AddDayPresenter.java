package com.example.android.fitdiary.Day;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Dao;
import com.example.android.fitdiary.Day.DietDay.DietDay;
import com.example.android.fitdiary.Day.TrainingDay.TrainingDay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

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
        else
            day = new TrainingDay(date);
    }

    public void save() {

            dao.getDatabase().collection("users")
                    .document(mAuth.getCurrentUser().getUid()).collection(type + "days")
                    .add(day)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            Log.d(TAG, "Added to list" + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error in adding List", e);

                        }
                    });

    }



    public Day getDay() {
        return day;
    }

    public interface IView {
        void savingSuccessful();

        void savingFailure();
    }
}

