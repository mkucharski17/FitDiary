package com.example.android.fitdiary.Firebase;

import com.google.firebase.firestore.FirebaseFirestore;

public class Dao {
    FirebaseFirestore database;

    public Dao() {
        database = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }
}
