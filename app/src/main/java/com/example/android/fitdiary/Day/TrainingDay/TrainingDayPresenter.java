package com.example.android.fitdiary.Day.TrainingDay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayPresenter;
import com.example.android.fitdiary.Day.DietDay.Food;
import com.example.android.fitdiary.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import static android.support.constraint.Constraints.TAG;


public class TrainingDayPresenter extends DayPresenter {
    private TrainingDay day;
    private Iview iview;

    public TrainingDayPresenter(Iview iview, Date date) {
        super();
        this.iview = iview;
        day = new TrainingDay(date);
    }

    public TrainingDay getDay() {
        return day;
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
                        day = document.toObject(TrainingDay.class);
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

    public interface Iview{
        void loadAdapter();
        void setListeners();
    }
}
