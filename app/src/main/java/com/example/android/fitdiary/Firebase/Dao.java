package com.example.android.fitdiary.Firebase;

import com.google.firebase.firestore.FirebaseFirestore;


/*
* Data acces object created in order to make communication between app and Firebase Firestore
* */
public class Dao {
    private FirebaseFirestore database;

    public Dao() {
        database = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }
}
