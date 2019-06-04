package com.example.android.fitdiary.Day;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.android.fitdiary.R;
import java.io.Serializable;


public class DaysListActivity extends AppCompatActivity implements AddDayFragment.CallBack, Serializable, DaysListPresenter.IView{

    private DaysListPresenter presenter;
    private ListView listView;
    private ArrayAdapter<Day> adapter;
    private Button addDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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


    public void  loadViews(){
        addDay = findViewById(R.id.add);
        addDay.setText("add day");
        listView =  findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this,R.layout.list_item,presenter.getDaysList());
        listView.setAdapter(adapter);

    }

    public void setListeners(){
        addDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButton();
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

    @Override
    public void onCallBack(Day day){
        presenter.addDay(day);
        showButton();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showButton() {
        addDay.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        addDay.setVisibility(View.GONE);
    }
}
