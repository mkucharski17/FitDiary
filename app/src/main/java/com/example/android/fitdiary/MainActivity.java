package com.example.android.fitdiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.fitdiary.Authentication.AuthenticationPresenter;
import com.example.android.fitdiary.Authentication.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements AuthenticationPresenter.IView {
    private static final String TAG = "MainActivity";
    private Button register;
    private Button signIn;
    private EditText email;
    private EditText password;
    private AuthenticationPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new AuthenticationPresenter(this);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
        signIn= findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.signInUser(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                }

                            }
                        });
                signingSuccesfull();
                Intent chooseIntent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(chooseIntent);
            }
        });
    }

    public void signingSuccesfull(){
        Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_LONG).show();
    }

}
