package com.example.android.fitdiary.Day.TrainingDay;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.fitdiary.Day.AddDayFragment;
import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayActivity;
import com.example.android.fitdiary.MainActivity;
import com.example.android.fitdiary.R;

import java.util.Date;

public class TrainingDayActivity extends DayActivity implements TrainingDayPresenter.Iview {
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
                    hideButton();
                    openAddFragment();
                }
            });
    }


    protected void openAddFragment(){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        AddTrainingFragment fragment = new AddTrainingFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.commit();
        transaction.addToBackStack(null);
    }
}
