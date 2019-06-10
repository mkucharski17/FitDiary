package com.example.android.fitdiary.Day.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.fitdiary.Presenters.BasePresenter;
import com.example.android.fitdiary.R;

/*
* DayActivity class - this is base class for TrainingDayActivity and DietDayActivity
*
* */

public abstract class DayActivity extends AppCompatActivity implements BasePresenter.Iview {
    protected Button add;
    protected Button delete;
    protected Button chart;
    protected ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_2);
        loadViews();
    }

    protected void loadViews() {
        add = findViewById(R.id.add);
        listView = findViewById(R.id.list);
        delete = findViewById(R.id.delete);
        chart = findViewById(R.id.chart);
    }

    @SuppressLint("SetTextI18n")
    protected void setText(String add) {
        this.add.setText(add);
        this.delete.setText("delete this day");
    }

}
