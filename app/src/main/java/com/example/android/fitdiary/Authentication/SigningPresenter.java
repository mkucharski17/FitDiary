package com.example.android.fitdiary.Authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.fitdiary.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import static android.support.constraint.Constraints.TAG;

public class SigningPresenter {
    private FirebaseAuth mAuth;
    private IView iview;


    public SigningPresenter(IView iview) {
        this.iview = iview;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            iview.runIntent();
                            iview.signingSuccessful();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            iview.signingFailure();

                        }
                    }
                });
    }

    public void signIn(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            iview.runIntent();
                            iview.signingSuccessful();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            iview.signingFailure();

                        }
                    }
                });
    }


    public interface IView{
        void loadViews();
        void setListeners();
        void signingSuccessful();
        void signingFailure();
        void runIntent();
    }

}
