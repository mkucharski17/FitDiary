package com.example.android.fitdiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity {
    TextView diet;
    TextView workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        diet = findViewById(R.id.diet);

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dietIntent = new Intent(ChooseActivity.this, DaysListActivity.class);
                startActivity(dietIntent);
            }
        });

        workout = findViewById(R.id.workout);

        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent workoutIntent = new Intent(ChooseActivity.this, DaysListActivity.class);
                startActivity(workoutIntent);
            }
        });
    }
}
