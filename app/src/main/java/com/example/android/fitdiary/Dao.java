package com.example.android.fitdiary;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.fitdiary.Authentication.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executor;

import static android.support.constraint.Constraints.TAG;

public class Dao {

    private FirebaseFirestore database;
    private FirebaseAuth mAuth;

    public Dao() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
    }

    public void  signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task  signIn(String email, String password){

        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public void save(String collection, Object object){
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }
}
