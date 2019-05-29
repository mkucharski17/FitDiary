package com.example.android.fitdiary.Day.TrainingDay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.fitdiary.Day.AddDayFragment;
import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DaysListPresenter;
import com.example.android.fitdiary.R;


public class AddTrainingFragment extends Fragment implements AddTrainingPresenter.IView{
    private CallBack callBack;
    private ListView listView;
    private ArrayAdapter<Exercise> adapter;
    private Button newExercise;
    private AddTrainingPresenter presenter;


    public AddTrainingFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callBack = (AddTrainingFragment.CallBack) getArguments().getSerializable("bundle");
        View v = inflater.inflate(R.layout.activity_list, container, false);
        presenter = new AddTrainingPresenter(this);
        listView = v.findViewById(R.id.list);
        newExercise = v.findViewById(R.id.add);
        adapter = new ArrayAdapter<>(getContext(),R.layout.list_item,presenter.getList());
        listView.setAdapter(adapter);
        return v;
    }


    private void close() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    public interface CallBack {
        void onCallBack(Day day);
    }

}
