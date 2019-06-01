package com.example.android.fitdiary.Day.DietDay;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayPresenter;
import com.example.android.fitdiary.Day.TrainingDay.TrainingDay;
import com.example.android.fitdiary.DaysComparator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;

import static android.support.constraint.Constraints.TAG;


public class DietDayPresenter extends DayPresenter {
    private DietDay day;
    private Iview iview;

    public DietDayPresenter(Iview iview) {
        super();
        this.iview = iview;
        day = new DietDay();
    }

    public void read() {

        DocumentReference docRef = dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("workoutdays")
                .document(day.toString());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        day = document.toObject(DietDay.class);
                        iview.loadAdapter();
                        iview.setListeners();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }


    public DietDay getDay() {
        return day;
    }

    public interface Iview{
        void loadAdapter();
        void setListeners();

    }



}
