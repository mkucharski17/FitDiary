package com.example.android.fitdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calendar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        ArrayList<String> dates;
        ListView listView =  findViewById(R.id.list);
        dates = new ArrayList<>();
        dates.add("12.12.2007");
        dates.add("13.12.2007");
        dates.add("14.12.2007");
        dates.add("15.12.2007");
        dates.add("16.12.2007");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item,dates);
        listView.setAdapter(adapter);
    }
}
