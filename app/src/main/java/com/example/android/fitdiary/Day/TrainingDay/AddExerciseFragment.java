package com.example.android.fitdiary.Day.TrainingDay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DaysListActivity;
import com.example.android.fitdiary.R;

import java.io.Serializable;
import java.util.List;


public class AddExerciseFragment extends Fragment implements AddExercisePresenter.IView{
    private ListView listView;
    private ArrayAdapter<Exercise> adapter;
    private Button newExercise;
    private AddExercisePresenter presenter;


    public AddExerciseFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        presenter = new AddExercisePresenter(this,(List<Exercise>) getArguments().getSerializable("allExerciseList"));
        listView =  v.findViewById(R.id.list);
        newExercise = v.findViewById(R.id.add);
        adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item,presenter.getAllExercisesList());
        listView.setAdapter(adapter);
        setListeners();

        return v;
    }


    public void setListeners(){
        newExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise e = new Exercise();
                close();
                openCreatingFragment(e, true);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise e = adapter.getItem(position);
                close();
                openCreatingFragment(e,false);
            }
        });
    }


    private void close() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();

    }


    private void openCreatingFragment(Exercise exercise, boolean New){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercise", exercise);
        bundle.putSerializable("new", New);
        bundle.putSerializable("bundle", getArguments().getSerializable("bundle"));
        FillExerciseFragment fragment = new FillExerciseFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}
