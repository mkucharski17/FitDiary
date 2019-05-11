package com.example.android.fitdiary;

public class User {
    private String Email;
    private String Password;


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


