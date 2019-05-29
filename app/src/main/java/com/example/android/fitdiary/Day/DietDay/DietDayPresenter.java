package com.example.android.fitdiary.Day.DietDay;
import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayPresenter;
import com.example.android.fitdiary.DaysComparator;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;


public class DietDayPresenter extends DayPresenter {
    private DietDay day;
    private Iview iview;

    public DietDayPresenter(Iview iview) {
        super();
        this.iview = iview;
        day = new DietDay();
    }

    public void read() {

        dao.getDatabase().collection("users").document(mAuth.getCurrentUser().getUid())
                .collection("dietdays")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Food food = documentSnapshot.toObject(Food.class);
                            day.addFood(food);
                        }
                        iview.loadAdapter();
                    }
                });

    }


    public DietDay getDay() {
        return day;
    }

    public interface Iview{
        void loadAdapter();

    }



}
