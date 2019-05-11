package com.example.android.fitdiary;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.fitdiary.Day.DaysListActivity;

public class RegistrationActivity extends AppCompatActivity implements RegistrationPresenter.IView {
    EditText email;
    EditText password;
    EditText login;
    Button createAccount;
    RegistrationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        createAccount = findViewById(R.id.createAccount);

        presenter = new RegistrationPresenter(this);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateUser(email.getText().toString(), password.getText().toString() , login.getText().toString() );
                Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
