package com.example.android.fitdiary.Day;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.android.fitdiary.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDayFragment extends Fragment implements AddDayPresenter.IView {
    private AddDayPresenter presenter;
    private CallBack click;
    private CalendarView date;
    private Button ok;


    public AddDayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        click = (CallBack) getArguments().getSerializable("bundle");
        View v = inflater.inflate(R.layout.fragment_add_date, container, false);
        date = v.findViewById(R.id.calendar);
        ok = v.findViewById(R.id.ok);
        loadPresenter(getArguments().getString("type"));
        setListeners();
        return v;
    }


    private void setListeners(){
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date selectedDate = new Date((year-1900),month,dayOfMonth);
                presenter.createDate(selectedDate);

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.save();
                click.onCallBack(presenter.getDay());
                close();
            }
        });

    }

    private void close() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    private void loadPresenter(String type){
        presenter = new AddDayPresenter(this,type);
    }



    public interface CallBack {
        void onCallBack(Day day);
    }

}




