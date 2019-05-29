package com.example.android.fitdiary.Day.DietDay;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.android.fitdiary.Day.AddDayFragment;
import com.example.android.fitdiary.Day.Day;
import com.example.android.fitdiary.Day.DayActivity;
import com.example.android.fitdiary.R;

public class DietDayActivity extends DayActivity implements DietDayPresenter.Iview {
    private DietDayPresenter presenter;
    private ArrayAdapter<Food> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DietDayPresenter(this);
        presenter.read();
        setContentView(R.layout.activity_list);
    }

    @Override
    public void loadAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.list_item, presenter.getDay().getFood());
        listView.setAdapter(adapter);
    }


    protected void setListeners(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButton();
                openAddFragment();
            }
        });
    }
    @Override
    protected void openAddFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        AddFoodFragment fragment = new AddFoodFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.commit();
        transaction.addToBackStack(null);
    }

}