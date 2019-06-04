package com.example.android.fitdiary.Day.DietDay;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.example.android.fitdiary.Day.DayActivity;
import com.example.android.fitdiary.Day.DaysListActivity;

import com.example.android.fitdiary.R;

import java.io.Serializable;
import java.util.Date;

public class DietDayActivity extends DayActivity implements DietDayPresenter.Iview,Serializable,FillFoodFragment.callBack {
    private DietDayPresenter presenter;
    private ArrayAdapter<Food> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getExtras();
        Date date = (Date) extra.get("day");
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
                dietIntent.putExtra("type", "diet");
                startActivity(dietIntent);
            }
        });

    }

    public void setText(){
        add.setText("add food");
        delete.setText("delete this day");
    }

    protected void openAddFragment() {

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
        presenter.addFood(f,isNew);
        presenter.saveDay();
    }


    public void deleteFood(Food f) {
        presenter.getDay().getFood().remove(f);
        presenter.saveDay();
    }

    public void removeFoodFromList(Food f){
        presenter.getAllFoodList().remove(f);
        presenter.deleteItemOfAllFoodList(f);
    }
}