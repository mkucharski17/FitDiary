package com.example.android.fitdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the register category
        TextView register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link RegisterActivity }
                Intent registerIntent = new Intent(MainActivity.this, Registration.class);

                //start new activity
                startActivity(registerIntent);
            }
        });

        // Find the View that shows the register category
        TextView calendar = findViewById(R.id.signIn);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link CalendarActivity }
                Intent calendarIntent = new Intent(MainActivity.this, Calendar.class);

                //start new activity
                startActivity(calendarIntent);
            }
        });


    }


}
