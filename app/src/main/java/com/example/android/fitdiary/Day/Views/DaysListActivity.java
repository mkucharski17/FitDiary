package com.example.android.fitdiary.Day.Views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.fitdiary.Day.Presenters.DaysListPresenter;
import com.example.android.fitdiary.Day.Models.Day;
import com.example.android.fitdiary.R;

import java.io.Serializable;


public class DaysListActivity extends AppCompatActivity implements AddDayFragment.CallBack, Serializable, DaysListPresenter.IView {

    private DaysListPresenter presenter;
    private ListView listView;
    private ArrayAdapter<Day> adapter;
    private Button addDay;
    private Button deleteList;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadPresenter();
        loadProgrssDialog();
        presenter.read();

    }

    private void loadPresenter() {
        Bundle extra = getIntent().getExtras();
        String type = extra.getString("type");
        presenter = new DaysListPresenter(this, type);
    }


    private void openFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("type", presenter.getType());
        bundle.putSerializable("bundle", this);
        AddDayFragment fragment = new AddDayFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.commit();
        transaction.addToBackStack(null);
    }


    public void loadViews() {
        addDay = findViewById(R.id.add);
        addDay.setText("add day");
        listView = findViewById(R.id.list);
        deleteList = findViewById(R.id.delete);
        deleteList.setText("delete list");
    }

    public void loadAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.list_item, presenter.getDaysList());
        listView.setAdapter(adapter);
    }

    public void setListeners() {

        deleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteList();
                presenter.getDaysList().clear();
                adapter.notifyDataSetChanged();
            }
        });


        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Day day = adapter.getItem(position);
                Intent intent = new Intent(DaysListActivity.this, presenter.getDestinationClass());
                intent.putExtra("day", day.getDate());
                startActivity(intent);
            }
        });
    }

    public void loadProgrssDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
    }

    public void showProgressDialog() {
        dialog.show();
    }

    public void hideProgressDialog() {
        dialog.dismiss();

    }

    @Override
    public void onCallBack(Day day) {
        presenter.addDay(day);
        adapter.notifyDataSetChanged();
    }


}
