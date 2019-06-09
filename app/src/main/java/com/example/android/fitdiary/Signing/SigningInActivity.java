package com.example.android.fitdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.fitdiary.Authentication.SigningPresenter;
import com.example.android.fitdiary.Authentication.RegistrationActivity;

public class MainActivity extends AppCompatActivity implements SigningPresenter.IView {
    private Button register;
    private Button signIn;
    private EditText email;
    private EditText password;
    private SigningPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new SigningPresenter(this);

        loadViews();
        setListeners();
    }

    private void loadViews() {
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        signIn = findViewById(R.id.signIn);
    }

    private void setListeners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signIn(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void signingFailure() {
        Toast.makeText(getApplicationContext(), "Signing in failed, try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void signingSuccessful() {
        Toast.makeText(getApplicationContext(), "Signing in successful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void runIntent() {
        Intent chooseIntent = new Intent(MainActivity.this, ChooseActivity.class);
        startActivity(chooseIntent);
    }
}
