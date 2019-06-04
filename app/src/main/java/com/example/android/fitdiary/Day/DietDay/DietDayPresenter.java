package com.example.android.fitdiary.Day.DietDay;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class DietDayPresenter extends DayPresenter {
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

    public void addFood(Food f , boolean isNew){
        if(isNew)
            allFoodList.add(f);
        day.addFood(f);
    }

    public DietDay getDay() {
        return day;
    }

    public List<Food> getAllFoodList() {
        return allFoodList;
    }

    public void read() {

        DocumentReference docRef = dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("dietdays")
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
                        iview.setText();
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

    public void deleteItemOfAllFoodList(Food f){

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

    public void deleteDay(){

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


    public interface Iview {
        void loadAdapter();

        void setListeners();

        void setText();

    }


}
