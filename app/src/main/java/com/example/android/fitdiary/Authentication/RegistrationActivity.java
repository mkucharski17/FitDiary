package com.example.android.fitdiary.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.fitdiary.ChooseActivity;
import com.example.android.fitdiary.MainActivity;
import com.example.android.fitdiary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

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

    @Override
    public void loadViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);
    }

    @Override
    public void setListeners() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.signUp(email.getText().toString(), password.getText().toString());
                checkData();
            }
        });
    }

    @Override
    public void checkData() {
        if (presenter.getValidate()) {
            signingSuccessful();
            Intent chooseIntent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(chooseIntent);
        } else {
            signingFailure();
        }
    }

    @Override
    public void signingSuccessful() {
        Toast.makeText(getApplicationContext(), "Signing up successful" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void signingFailure() {
        Toast.makeText(getApplicationContext(), "Signing up failed, try again"+ presenter.getValidate(), Toast.LENGTH_LONG).show();
    }


}
