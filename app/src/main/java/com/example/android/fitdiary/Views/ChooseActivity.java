package com.example.android.fitdiary.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.fitdiary.Day.Views.DaysListActivity;
import com.example.android.fitdiary.R;

public class ChooseActivity extends AppCompatActivity {
    private TextView diet;
    private TextView workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        loadViews();
        setListeners();

    }

    private void loadViews() {
        diet = findViewById(R.id.diet);
        workout = findViewById(R.id.workout);
    }

    private void setListeners() {
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent workoutIntent = new Intent(ChooseActivity.this, DaysListActivity.class);
                workoutIntent.putExtra("type", "workout");
                startActivity(workoutIntent);

            }
        });

        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dietIntent = new Intent(ChooseActivity.this, DaysListActivity.class);
                dietIntent.putExtra("type", "diet");
                startActivity(dietIntent);

            }
        });

    }
}
