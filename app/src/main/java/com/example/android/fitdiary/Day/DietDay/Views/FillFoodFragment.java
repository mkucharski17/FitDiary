package com.example.android.fitdiary.Day.DietDay.Views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.fitdiary.Day.DietDay.Presenters.FillFoodPresenter;
import com.example.android.fitdiary.Day.DietDay.Models.Food;
import com.example.android.fitdiary.Views.BaseFragment;
import com.example.android.fitdiary.R;

public class FillFoodFragment extends BaseFragment implements FillFoodPresenter.IView{

    private callBack callBack;
    private FillFoodPresenter presenter;
    private EditText name;
    private EditText carbohydrates;
    private EditText protein;
    private EditText fat;
    private EditText portion;
    private Button ok;
    private Button deleteThis;
    private Button deleteFromList;


    /*
    * Fragment created to set macros and portion of new food
    * */
    public FillFoodFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        assert getArguments() != null;
        callBack = (callBack) getArguments().getSerializable("bundle");
        View v = inflater.inflate(R.layout.fragment_fill_food, container, false);
        loadViews(v);
        loadPresenter();
        setListeners();
        setTexts();
        return v;
    }

    public void loadPresenter() {
        presenter = new FillFoodPresenter(this);
        presenter.setNew((boolean) getArguments().get("new"));
        presenter.setFood((Food) getArguments().get("food"));

    }

    public void loadViews(View v) {
        name = v.findViewById(R.id.name);
        carbohydrates = v.findViewById(R.id.carbohydrates);
        protein = v.findViewById(R.id.protein);
        fat = v.findViewById(R.id.fat);
        portion = v.findViewById(R.id.portion);
        ok = v.findViewById(R.id.ok);
        deleteThis = v.findViewById(R.id.deleteThis);
        deleteFromList = v.findViewById(R.id.deleteAll);
    }

    /*
     * set info about macros from editTexts
     * */

    public void setInfo() {
        presenter.getFood().setName(name.getText().toString());
        presenter.getFood().getMacroComponents().setCarbohydrates(Float.parseFloat(carbohydrates.getText().toString()));
        presenter.getFood().getMacroComponents().setProtein(Float.parseFloat(protein.getText().toString()));
        presenter.getFood().getMacroComponents().setFat(Float.parseFloat(fat.getText().toString()));
        presenter.getFood().setPortion(Float.parseFloat(portion.getText().toString()));
        presenter.getFood().computeKcal();
    }


    /*
     * setting text of editText objects
     * */
    @SuppressLint("SetTextI18n")
    public void setTexts() {
        name.setText(presenter.getFood().getName());
        carbohydrates.setText(Float.toString(presenter.getFood().getMacroComponents().getCarbohydrates()));
        protein.setText(Float.toString(presenter.getFood().getMacroComponents().getProtein()));
        fat.setText(Float.toString(presenter.getFood().getMacroComponents().getFat()));
        portion.setText(Float.toString(presenter.getFood().getPortion()));

    }

    public void setListeners() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInfo();
                if (presenter.isNew())
                    presenter.saveNewFood();

                callBack.addFood(presenter.getFood(), presenter.isNew());
                close();
            }
        });

        deleteThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.deleteFood(presenter.getFood());
                close();
            }
        });

        deleteFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.removeFoodFromList(presenter.getFood());
                close();
            }
        });
    }

    public void hideButtons(){
        deleteThis.setVisibility(View.GONE);
        deleteFromList.setVisibility(View.GONE);
    }

    public interface callBack {
        void addFood(Food d, boolean isNew);

        void deleteFood(Food f);

        void removeFoodFromList(Food f);


    }
}
