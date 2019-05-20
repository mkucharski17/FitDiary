package com.example.android.fitdiary.Day;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.android.fitdiary.R;
import java.io.Serializable;


public class DaysListActivity extends AppCompatActivity implements AddDayFragment.CallBack, Serializable, DaysListPresenter.IView{

    private DaysListPresenter presenter;
    private FrameLayout container;
    private ListView listView;
    private ArrayAdapter<Day> adapter;
    private Button addDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_list);
        loadPresenter();
        presenter.read();
    }

    private void loadPresenter(){
        Bundle extra = getIntent().getExtras();
        String type = extra.getString("type");
        presenter = new DaysListPresenter(this,type);

    }



    private void openFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("type",presenter.getType());
        bundle.putSerializable("bundle", this);
        AddDayFragment fragment = new AddDayFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.commit();
        transaction.addToBackStack(null);
    }

    @Override
    public void onCallBack(Day day){
        presenter.addDay(day);
        showButtons();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showButtons() {
        addDay.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtons() {
        addDay.setVisibility(View.GONE);
    }


    public void  loadViews(){
        listView =  findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this,R.layout.list_item,presenter.getDaysList());
        listView.setAdapter(adapter);
        addDay = findViewById(R.id.add);
        container = findViewById(R.id.container);
    }

    public void setListeners(){
        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButtons();
                openFragment();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
            }
        });

    }
}
