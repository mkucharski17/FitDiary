package com.example.android.fitdiary.Day.DietDay.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.fitdiary.Day.DietDay.Presenters.AddFoodPresenter;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import com.example.android.fitdiary.Views.BaseFragment;
import com.example.android.fitdiary.R;

import java.util.List;


public class AddFoodFragment extends BaseFragment implements AddFoodPresenter.IView {
    private ListView listView;
    private ArrayAdapter<Food> adapter;
    private Button newFood;
    private Button delete;
    private AddFoodPresenter presenter;


    public AddFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_list, container, false);
        presenter = new AddFoodPresenter(this, (List<Food>) getArguments().getSerializable("allFoodList"));
        listView = v.findViewById(R.id.list);
        delete = v.findViewById(R.id.delete);
        hideDeleteButton();
        newFood = v.findViewById(R.id.add);
        newFood.setText("create new food");
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, presenter.getAllFoodList());
        listView.setAdapter(adapter);
        setListeners();

        return v;
    }


    public void setListeners() {
        newFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food f = new Food();
                close();
                openFillFoodFragment(f, true);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food f = adapter.getItem(position);
                close();
                openFillFoodFragment(f, false);
            }
        });

    }

    private void openFillFoodFragment(Food f, boolean New) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("food", f);
        bundle.putSerializable("new", New);
        bundle.putSerializable("bundle", getArguments().getSerializable("bundle"));
        FillFoodFragment fragment = new FillFoodFragment();
        fragment.setArguments(bundle);
        transaction.add(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void hideDeleteButton() {
        delete.setVisibility(View.GONE);
    }

}
