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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity implements AuthenticationPresenter.IView {
    public static final String TAG = MainActivity.class.getSimpleName();
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
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });;
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
