package com.example.android.fitdiary.Signing.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.android.fitdiary.R;
import com.example.android.fitdiary.Signing.Presenters.SigningPresenter;

/*
 * RegistratioActivity - class using to sign up User in app
 * */

public class RegistrationActivity extends AppCompatActivity implements SigningPresenter.IView {
    private EditText email;
    private EditText password;
    private Button createAccount;
    private SigningPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        presenter = new SigningPresenter(this);

        loadViews();
        setListeners();
    }


    private void loadViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);
    }

    private void setListeners() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUp(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void signingSuccessful() {
        Toast.makeText(getApplicationContext(), "Signing up successful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void signingFailure() {
        Toast.makeText(getApplicationContext(), "Signing up failed, try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void runIntent() {
        Intent mainIntent = new Intent(RegistrationActivity.this, SigningInActivity.class);
        startActivity(mainIntent);
    }
}
