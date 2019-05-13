package com.example.android.fitdiary.Registration;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {
    private User user;
    private FirebaseFirestore database;

    public void saveUser(User user){
        database = FirebaseFirestore.getInstance();
        database.collection("users").add(user);
    }
}
