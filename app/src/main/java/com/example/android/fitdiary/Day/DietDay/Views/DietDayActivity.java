package com.example.android.fitdiary.Day.DietDay.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.android.fitdiary.Day.Views.DayActivity;

import com.example.android.fitdiary.Day.Views.DaysListActivity;
import com.example.android.fitdiary.Day.DietDay.Presenters.DietDayPresenter;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import com.example.android.fitdiary.Presenters.BasePresenter;
import com.example.android.fitdiary.R;

import java.io.Serializable;
import java.util.Date;

/*
* Activity using by user to check list of food eaten in one day
* */

public class DietDayActivity extends DayActivity implements BasePresenter.Iview, Serializable, FillFoodFragment.callBack {
    private DietDayPresenter presenter;
    private ArrayAdapter<Food> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setText("add food");
        Bundle extra = getIntent().getExtras();
        Date date = (Date) extra.get("day");                     //getting day selected in DaysListActivity
        presenter = new DietDayPresenter(this, date);
        presenter.read();
    }

    public void loadAdapter() {
        adapter = new ArrayAdapter<>(this, R.layout.list_item, presenter.getDay().getFood());
        listView.setAdapter(adapter);
    }

    public void setListeners() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFragment();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food f = adapter.getItem(position);
                presenter.removeFood(f);
                openFillFoodFragment(f);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteDay();
                Intent dietIntent = new Intent(DietDayActivity.this, DaysListActivity.class);
                dietIntent.putExtra("type", "diet");          //send type of day to next Activity
                startActivity(dietIntent);
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPieCHartFragment();
            }
        });
    }

    /*
    * open AddFoodFragment and send list of all food created by user
    * */
    private void openAddFragment() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("allFoodList", (Serializable) presenter.getAllFoodList());
        bundle.putSerializable("bundle", this);
        AddFoodFragment fragment = new AddFoodFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*
    * this method opens Fragment with chart, and send data which will be shown on diagram
    * */
    private void openPieCHartFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", (Serializable) presenter.getDay().getFood());
        bundle.putSerializable("bundle", this);
        PieChartFragment fragment = new PieChartFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    /*
    * opening FillFoodFragment and send food to fill
    * */
    private void openFillFoodFragment(Food f) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", f);
        bundle.putSerializable("new", false);
        bundle.putSerializable("bundle", this);
        FillFoodFragment fragment = new FillFoodFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFood(Food f, boolean isNew) {
        presenter.addFood(f, isNew);
        presenter.saveDay();
        adapter.notifyDataSetChanged();
    }


    public void deleteFood(Food f) {
        presenter.getDay().getFood().remove(f);
        presenter.saveDay();
        adapter.notifyDataSetChanged();
    }

    public void removeFoodFromList(Food f) {
        presenter.getAllFoodList().remove(f);
        presenter.deleteItemOfAllFoodList(f);
        adapter.notifyDataSetChanged();
    }
}