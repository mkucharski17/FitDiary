package com.example.android.fitdiary.Day.TrainingDay;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android.fitdiary.Day.AddDayFragment;
import com.example.android.fitdiary.Day.DayActivity;
import com.example.android.fitdiary.R;

import java.io.Serializable;
import java.util.Date;

public class TrainingDayActivity extends DayActivity implements TrainingDayPresenter.Iview, Serializable, FillExerciseFragment.callBack {
    private TrainingDayPresenter presenter;
    private ArrayAdapter<Exercise> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getExtras();
        Date date = (Date)extra.get("day");
        presenter = new TrainingDayPresenter(this,date);
        presenter.read();

    }

    public void loadAdapter(){
        adapter = new ArrayAdapter<>(this,R.layout.list_item,presenter.getDay().getExercises());
        listView.setAdapter(adapter);
    }

    public void setListeners(){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openAddFragment();
                    hideButton();
                }
            });
    }


    protected void openAddFragment(){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("allExerciseList", (Serializable) presenter.getAllExercisesList());
        bundle.putSerializable("bundle", this);
        AddExerciseFragment fragment = new AddExerciseFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    public void showButton(){
        add.setVisibility(View.VISIBLE);
    }

    public void OnCallBack(Exercise e){
        presenter.getDay().addExercise(e);
        presenter.saveDay();
        showButton();

    }
}
