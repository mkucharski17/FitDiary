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

import com.example.android.fitdiary.R;

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
        presenter = new AddDayPresenter(this);

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "." + month + "." + year;
                presenter.updateDate(selectedDate);

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onCallBack(presenter.getDay().getDate());
                close();
            }
        });
        return v;
    }

    public void close() {
        FragmentManager manager = getFragmentManager();
        manager.popBackStack();
    }

    public interface CallBack {
        void onCallBack(String s);
    }

}




