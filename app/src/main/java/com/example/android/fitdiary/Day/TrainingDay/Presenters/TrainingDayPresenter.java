package com.example.android.fitdiary.Day.TrainingDay.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Day.Presenters.DayPresenter;
import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.Day.TrainingDay.Models.TrainingDay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class TrainingDayPresenter extends DayPresenter {
    private TrainingDay day;
    private List<Exercise> allExercisesList;
    private Iview iview;

    public TrainingDayPresenter(Iview iview, Date date) {
        super();
        this.iview = iview;
        day = new TrainingDay(date);
        allExercisesList = new ArrayList<>();
    }

    public List<Exercise> getAllExercisesList() {
        return allExercisesList;
    }

    public void removeExercise(Exercise e) {
        day.getExercises().remove(e);
    }

    public void addExercise(Exercise e, boolean isNew) {
        if (isNew)
            allExercisesList.add(e);
        day.addExercise(e);
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
        readAllExercsiesList();
    }


    private void readAllExercsiesList() {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("exercises")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Exercise exercise = documentSnapshot.toObject(Exercise.class);
                            allExercisesList.add(exercise);
                        }
                    }
                });
    }

    public void deleteItemOfAllExerciseList(Exercise e) {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("exercises").document(e.getName())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });


    }


    public void saveDay() {

        dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("workoutdays")
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

    public void deleteDay() {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("workoutdays").document(day.toString())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }

}