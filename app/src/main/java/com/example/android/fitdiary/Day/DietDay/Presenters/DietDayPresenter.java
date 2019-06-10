package com.example.android.fitdiary.Day.DietDay.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.android.fitdiary.Day.DietDay.Models.DietDay;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import com.example.android.fitdiary.Presenters.BasePresenter;
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

/*
* DietDayPresenter - presenter for DietDayActivity
* */
public class DietDayPresenter extends BasePresenter {
    private DietDay day;
    private List<Food> allFoodList;
    private Iview iview;


    public DietDayPresenter(Iview iview, Date date) {
        super();
        this.iview = iview;
        day = new DietDay(date);
        allFoodList = new ArrayList<>();
    }

    public void removeFood(Food f) {
        day.getFood().remove(f);
    }


    // Add food to list of user's food if it wasn't there earlier
    public void addFood(Food f, boolean isNew) {
        if (isNew)
            allFoodList.add(f);
        day.addFood(f);
    }

    public DietDay getDay() {
        return day;
    }

    public List<Food> getAllFoodList() {
        return allFoodList;
    }

     /*
     * method which read diet day from Firebase Firestore
     * */
    public void read() {

        /*
        * localize document
        * */
        DocumentReference docRef = dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("dietdays")
                .document(day.toString());
        /*
        * get document and set day of this presenter
        * */
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
        readAllFoodList();
    }


    /*
    * getting all food list from data base
    * */
    private void readAllFoodList() {
        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("food")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Food food = documentSnapshot.toObject(Food.class);
                            allFoodList.add(food);
                        }
                    }
                });
    }
    /*
    * delete particular food from list of user's food
    * */
    public void deleteItemOfAllFoodList(Food f) {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("food").document(f.getName())
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

    /*
    * save current day
    * */
    public void saveDay() {

        dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("dietdays")
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

    /*
     * delete current day
     * */

    public void deleteDay() {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("dietdays").document(day.toString())
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
