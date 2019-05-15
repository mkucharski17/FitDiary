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

public class RegistrationActivity extends AppCompatActivity implements AuthenticationPresenter.IView {
    private EditText email;
    private EditText password;
    private Button createAccount;
    private AuthenticationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);

        presenter = new AuthenticationPresenter(this);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerUser(email.getText().toString(), password.getText().toString());
                signingSuccesfull();


                Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    @Override
    public void signingSuccesfull(){
        Toast.makeText(getApplicationContext(),"user saved", Toast.LENGTH_LONG).show();
    }


}
