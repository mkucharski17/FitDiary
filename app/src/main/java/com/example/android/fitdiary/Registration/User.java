package com.example.android.fitdiary.Registration;

import com.example.android.fitdiary.Day.DietDay.DietDay;
import com.example.android.fitdiary.Day.TrainingDay.TrainingDay;

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

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }


    public User() {
    }


    public User(String email, String password ,String login) {
        Email = email;
        Password = password;
        Login = login ;
    }
}


