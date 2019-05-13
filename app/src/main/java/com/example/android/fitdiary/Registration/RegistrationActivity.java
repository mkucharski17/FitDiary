package com.example.android.fitdiary.Registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.fitdiary.MainActivity;
import com.example.android.fitdiary.R;

public class RegistrationActivity extends AppCompatActivity implements RegistrationPresenter.IView {
    private EditText email;
    private EditText password;
    private EditText login;
    private Button createAccount;
    private RegistrationPresenter presenter;

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
                presenter.registerUser(email.getText().toString(), password.getText().toString() , login.getText().toString() );
                savedSuccesfull();
                Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    @Override
    public void savedSuccesfull(){
        Toast.makeText(getApplicationContext(),"user saved", Toast.LENGTH_LONG).show();
    }
}
