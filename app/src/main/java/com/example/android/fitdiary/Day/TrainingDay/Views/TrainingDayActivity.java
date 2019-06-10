package com.example.android.fitdiary.Day.TrainingDay.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.android.fitdiary.Day.Views.DayActivity;
import com.example.android.fitdiary.Day.Views.DaysListActivity;
import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.Day.TrainingDay.Presenters.TrainingDayPresenter;
import com.example.android.fitdiary.Presenters.BasePresenter;
import com.example.android.fitdiary.R;

import java.io.Serializable;
import java.util.Date;

public class TrainingDayActivity extends DayActivity implements BasePresenter.Iview, Serializable, FillExerciseFragment.callBack {
    private TrainingDayPresenter presenter;
    private ArrayAdapter<Exercise> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("add exercise");
        Bundle extra = getIntent().getExtras();
        assert extra != null;
        Date date = (Date) extra.get("day");
        presenter = new TrainingDayPresenter(this, date);
        presenter.read();
    }

    public void loadAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.list_item, presenter.getDay().getExercises());
        listView.setAdapter(adapter);
    }

    public void setListeners() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFragment();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise e = adapter.getItem(position);
                presenter.removeExercise(e);
                openFillingFragment(e);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteDay();
                Intent workoutIntent = new Intent(TrainingDayActivity.this, DaysListActivity.class);
                workoutIntent.putExtra("type", "workout");
                startActivity(workoutIntent);
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChartFragment();
            }
        });
    }

    private void openChartFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercises", (Serializable) presenter.getDay().getExercises());
        bundle.putSerializable("bundle", this);
        BarChartFragment fragment = new BarChartFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    protected void openAddFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("allExerciseList", (Serializable) presenter.getAllExercisesList());
        bundle.putSerializable("bundle", this);
        AddExerciseFragment fragment = new AddExerciseFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void openFillingFragment(Exercise exercise) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercise", exercise);
        bundle.putSerializable("new", false);
        bundle.putSerializable("bundle", this);
        FillExerciseFragment fragment = new FillExerciseFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addExercise(Exercise e, boolean isNew) {
        presenter.addExercise(e, isNew);
        presenter.saveDay();
    }


    public void deleteExercise(Exercise e) {
        presenter.getDay().getExercises().remove(e);
        presenter.saveDay();
    }

    public void removeExerciseFromList(Exercise e) {
        presenter.getAllExercisesList().remove(e);
        presenter.deleteItemOfAllExerciseList(e);
    }

}
