package com.example.android.fitdiary.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.fitdiary.MainActivity;
import com.example.android.fitdiary.R;

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
        Toast.makeText(getApplicationContext(), "Signing up successful" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void signingFailure() {
        Toast.makeText(getApplicationContext(), "Signing up failed, try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void runIntent(){
        Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}