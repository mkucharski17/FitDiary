package com.example.android.fitdiary.Day.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Firebase.Dao;
import com.example.android.fitdiary.Day.DietDay.Views.DietDayActivity;
import com.example.android.fitdiary.Day.Models.Day;
import com.example.android.fitdiary.Day.TrainingDay.Views.TrainingDayActivity;
import com.example.android.fitdiary.Day.Models.DaysComparator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.support.constraint.Constraints.TAG;


public class DaysListPresenter {
    private List<Day> DaysList;
    private IView iview;
    private String type;
    private Dao dao;
    private FirebaseAuth mAuth;

    public DaysListPresenter(IView iview, String type) {
        DaysList = new ArrayList<>();
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
        this.type = type;
        this.iview = iview;
    }

    public String getType() {
        return type;
    }

    public List<Day> getDaysList() {
        return DaysList;
    }

    public void addDay(Day day) {
        DaysList.add(day);
        Collections.sort(DaysList, new DaysComparator());
    }

    public Class getDestinationClass() {
        if (type.equals("diet"))
            return DietDayActivity.class;

        return TrainingDayActivity.class;
    }

    public void deleteList() {

        for (int i = 0; i < DaysList.size(); i++) {
            Day d = DaysList.get(i);
            dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                    .collection(type + "days").document(d.toString())
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "List of days successfully deleted!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "Error deleting List of days", e);
                        }
                    });
        }

    }


    public void read() {
        iview.showProgressDialog();

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection(type + "days")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Day day = documentSnapshot.toObject(Day.class);
                            DaysList.add(day);
                        }
                        iview.loadViews();
                        iview.loadAdapter();
                        iview.setListeners();
                        Collections.sort(DaysList, new DaysComparator());
                        iview.hideProgressDialog();
                    }
                });

    }

    public interface IView {

        void loadViews();

        void setListeners();

        void loadAdapter();

        void showProgressDialog();

        void hideProgressDialog();


    }
}
