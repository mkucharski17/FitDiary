package com.example.android.fitdiary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Calendar extends AppCompatActivity implements AddDateFragment.CallBack, Serializable {

    private ArrayList<String> dates;
    private FrameLayout container;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    private Button addTrainingDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dates = new ArrayList<>();
        listView =  findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this,R.layout.list_item,dates);
        listView.setAdapter(adapter);
        addTrainingDay = findViewById(R.id.add);
        container = findViewById(R.id.container);

        dates.add("duap");
        dates.add("duap");
        dates.add("duap");
        dates.add("duap");
        dates.add("duap");
        dates.add("duap");



        addTrainingDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View b = findViewById(R.id.add);
                b.setVisibility(View.GONE);
                openFragment();
            }
        });


    }

    public void openFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("dupa", this);
        AddDateFragment fragment = new AddDateFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.commit();
    }

    @Override
    public void onCallBack(String s){
        dates.add(s);
        View b = findViewById(R.id.add);
        b.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }
}
