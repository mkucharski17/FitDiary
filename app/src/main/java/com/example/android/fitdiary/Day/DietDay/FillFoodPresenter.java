package com.example.android.fitdiary.Day.DietDay;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.Dao;
import com.example.android.fitdiary.Day.TrainingDay.Exercise;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.constraint.Constraints.TAG;

public class FillFoodPresenter {

    private Food food;
    private boolean New;
    private IView iView;
    private Dao dao;
    private FirebaseAuth mAuth;

    public FillFoodPresenter( IView iView) {
        this.iView = iView;
        dao = new Dao();
        mAuth = FirebaseAuth.getInstance();
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

    public void setNew(boolean aNew) {
        New = aNew;
    }

    public void saveNewFood(){
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


    public interface IView{

    }
}
