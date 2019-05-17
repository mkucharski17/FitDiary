package com.example.android.fitdiary;

import com.google.firebase.firestore.FirebaseFirestore;

public class Saver {

    private FirebaseFirestore database;

    public Saver() {
        database = FirebaseFirestore.getInstance();
    }


    public void save(String collection, Object object) {
    }


}
