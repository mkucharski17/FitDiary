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
import android.widget.Toast;

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

        presenter = new DaysListPresenter(this);
        listView =  findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this,R.layout.list_item,presenter.getDaysList());
        listView.setAdapter(adapter);
        addDay = findViewById(R.id.add);
        container = findViewById(R.id.container);


        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showButton();
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

    public void openFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle", this);
        AddDayFragment fragment = new AddDayFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container,fragment);
        transaction.commit();
        transaction.addToBackStack(null);
    }

    @Override
    public void onCallBack(String s){
        Toast.makeText(getApplicationContext(),"user saved", Toast.LENGTH_LONG).show();
        presenter.addDay(s);
        hideButton();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideButton() {
        addDay.setVisibility(View.VISIBLE);
    }

    @Override
    public void showButton() {
        addDay.setVisibility(View.GONE);
    }
}
