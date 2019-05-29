package com.example.android.fitdiary.Day;
import android.content.Intent;

import com.example.android.fitdiary.Dao;
import com.example.android.fitdiary.Day.DietDay.DietDay;
import com.example.android.fitdiary.Day.DietDay.DietDayActivity;
import com.example.android.fitdiary.Day.TrainingDay.TrainingDayActivity;
import com.example.android.fitdiary.DaysComparator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    public Class getDestinationClass(){
        if(type.equals("diet"))
            return DietDayActivity.class;

        return TrainingDayActivity.class;
    }


    public void read() {

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
                        iview.setListeners();
                        Collections.sort(DaysList, new DaysComparator());
                    }
                });

    }

    public interface IView {
        void hideButton();

        void showButton();

        void loadViews();

        void setListeners();

    }
}
