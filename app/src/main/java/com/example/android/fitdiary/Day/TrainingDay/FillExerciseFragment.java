package com.example.android.fitdiary.Day.TrainingDay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.fitdiary.R;


public class FillExerciseFragment extends BaseFragment implements FillExercisePresenter.IView {
    private callBack callBack;
    private FillExercisePresenter presenter;
    private EditText name;
    private EditText reps;
    private EditText sets;
    private EditText weight;
    private Button ok;
    private Button deleteThis;
    private Button deleteFromList;


    public FillExerciseFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callBack = (callBack) getArguments().getSerializable("bundle");
        View v = inflater.inflate(R.layout.fragment_fill_exercise, container, false);
        loadViews(v);
        loadPresenter();
        setListener();
        setTexts();
        return v;
    }

    public void loadPresenter(){
        presenter = new FillExercisePresenter(this);
        presenter.setNew((boolean)getArguments().get("new"));
        presenter.setExercise((Exercise) getArguments().get("exercise"));

    }

    public void loadViews(View v){
        name = v.findViewById(R.id.name);
        reps = v.findViewById(R.id.reps);
        sets = v.findViewById(R.id.sets);
        weight = v.findViewById(R.id.weight);
        ok = v.findViewById(R.id.ok);
        deleteThis = v.findViewById(R.id.deleteThis);
        deleteFromList = v.findViewById(R.id.deleteAll);


    }

    public void setInfo(){
        presenter.getExercise().setName(name.getText().toString());
        presenter.getExercise().setSets(Integer.parseInt(sets.getText().toString()));
        presenter.getExercise().setReps(Integer.parseInt(reps.getText().toString()));
        presenter.getExercise().setWeight(Integer.parseInt(weight.getText().toString()));
    }

    public void setTexts(){
        name.setText(presenter.getExercise().getName());
        sets.setText(Integer.toString(presenter.getExercise().getSets()));
        reps.setText(Integer.toString(presenter.getExercise().getReps()));
        weight.setText(Integer.toString(presenter.getExercise().getWeight()));

    }

    public void setListener(){
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfo();
                if(presenter.isNew())
                    presenter.saveNewExercise();

                callBack.addExercise(presenter.getExercise(),presenter.isNew());
                close();
            }
        });

        deleteThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.deleteExercise(presenter.getExercise());
                close();
            }
        });

        deleteFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.removeExerciseFromList(presenter.getExercise());
                close();
            }
        });




    }

    public interface callBack{
        void addExercise(Exercise e,boolean isNew);
        void deleteExercise(Exercise e);
        void removeExerciseFromList(Exercise e);

    }

}
