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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class DaysListPresenter {
    private ArrayList<Day> DaysList;
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
    public ArrayList<Day> getDaysList() {
        return DaysList;
    }

    public void addDay(String date){
        Day day;
       if(type.equals("diet"))
         day = new DietDay(date);
       else
           day = new TrainingDay(date);
        DaysList.add(day);
    }

    public void save(){

        for(int i = 0; i < DaysList.size() ; i++) {
            dao.getDatabase().collection("users")
                    .document(mAuth.getCurrentUser().getUid()).collection(type)
                    .add(DaysList.get(i))
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            iview.savingSuccessful();
                            Log.d(TAG, "List added " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error in adding List", e);
                            iview.savingFailure();
                        }
                    });

        }

    }

    public interface IView{
        void hideButtons();
        void showButtons();
        void savingSuccessful();
        void savingFailure();

    }
}
