package com.example.android.fitdiary;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password);
    }
}
