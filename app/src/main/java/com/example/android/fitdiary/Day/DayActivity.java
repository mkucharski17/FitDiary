package com.example.android.fitdiary.Day;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.fitdiary.R;


public abstract class DayActivity extends AppCompatActivity implements DayPresenter.Iview{
    protected Button add;
    protected Button delete;
    protected ListView listView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadViews();
    }

    protected void loadViews(){
        add = findViewById(R.id.add);
        listView =  findViewById(R.id.list);
        delete = findViewById(R.id.delete);
    }


    protected abstract void openAddFragment();

}
