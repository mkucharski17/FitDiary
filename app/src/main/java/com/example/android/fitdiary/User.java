package com.example.android.fitdiary;

import com.example.android.fitdiary.Day.DietDay;
import com.example.android.fitdiary.Day.TrainingDay;

import java.util.ArrayList;

public class User {
    private String Login;
    private String Email;
    private String Password;
    private ArrayList<TrainingDay> trainingDays;
    private ArrayList<DietDay> dietDays;


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User() {
    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }
}


