package com.example.android.fitdiary.Day.TrainingDay.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.fitdiary.Day.TrainingDay.Presenters.AddExercisePresenter;
import com.example.android.fitdiary.Views.BaseFragment;
import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.R;

import java.util.List;


public class AddExerciseFragment extends BaseFragment implements AddExercisePresenter.IView {
    private ListView listView;
    private ArrayAdapter<Exercise> adapter;
    private Button newExercise;
    private Button delete;
    private AddExercisePresenter presenter;


    public AddExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        presenter = new AddExercisePresenter(this, (List<Exercise>) getArguments().getSerializable("allExerciseList"));
        listView = v.findViewById(R.id.list);
        delete = v.findViewById(R.id.delete);
        hideDeleteButton();
        newExercise = v.findViewById(R.id.add);
        newExercise.setText("create new exercise");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, presenter.getAllExercisesList());
        listView.setAdapter(adapter);
        setListeners();

        return v;
    }


    public void setListeners() {
        newExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise e = new Exercise();
                close();
                openFillFragment(e, true);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exercise e = adapter.getItem(position);
                close();
                openFillFragment(e, false);
            }
        });

    }

    private void openFillFragment(Exercise exercise, boolean New) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercise", exercise);
        bundle.putSerializable("new", New);
        bundle.putSerializable("bundle", getArguments().getSerializable("bundle"));
        FillExerciseFragment fragment = new FillExerciseFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void hideDeleteButton() {
        delete.setVisibility(View.GONE);
    }

}
