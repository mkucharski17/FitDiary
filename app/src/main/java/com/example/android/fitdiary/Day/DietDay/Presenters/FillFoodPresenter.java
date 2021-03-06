package com.example.android.fitdiary.Day.DietDay.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Firebase.Dao;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import com.example.android.fitdiary.Presenters.BasePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.constraint.Constraints.TAG;


/*
 * presenter which man FillFoodactivity
 */

public class FillFoodPresenter extends BasePresenter {

    private Food food;
    private boolean New;
    private IView iView;

    public FillFoodPresenter(IView iView) {
        super();
        this.iView = iView;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean isNew() {
        return New;
    }

    public void setNew(boolean New) {
        this.New = New;
        if(New)
            iView.hideButtons();

    }

    /*
    * save new product created by user
    * */
    public void saveNewFood() {
        dao.getDatabase().collection("users")
                .document(mAuth.getCurrentUser().getUid()).collection("food")
                .document(food.getName())
                .set(food)
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


    public interface IView {
        void hideButtons();

    }
}
