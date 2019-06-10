package com.example.android.fitdiary.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.fitdiary.Day.Views.DaysListActivity;
import com.example.android.fitdiary.R;

/*
* ChooseActivity class - in this view user can choose which type of day will be considered
* */
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


    /* click on button workout means you choose trainingDay as a type of day in dayslistActivity and send type "wokruout as" information  about that*/
    /* click on button diet means you choose DietDay as a type of day in dayslistActivity and send type "diet" as information  about that*/
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
