package com.example.android.fitdiary.Authentication;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.fitdiary.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;

public class SigningPresenter {
    private FirebaseAuth mAuth;
    private IView iview;
    private boolean validate;


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
                            validate = true;
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            validate = false;
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
                            validate = true;
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            validate = false;
                        }
                    }
                });
    }


    public boolean getValidate() {
        return validate;
    }

    public interface IView{
        void loadViews();
        void setListeners();
        void checkData();
        void signingSuccessful();
        void signingFailure();
    }

}
