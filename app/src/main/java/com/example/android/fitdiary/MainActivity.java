package com.example.android.fitdiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.fitdiary.Day.ChooseActivity;
import com.example.android.fitdiary.Registration.RegistrationActivity;
public class MainActivity extends AppCompatActivity {
    private Button register;
    private Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                Intent chooseIntent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(chooseIntent);
            }
        });
    }
}
