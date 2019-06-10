package com.example.android.fitdiary.Day.DietDay.Presenters;

import com.example.android.fitdiary.Day.DietDay.Models.Food;

import java.util.ArrayList;
import java.util.List;

/*
* Presenter for AddfoodActivity class
* */

public class AddFoodPresenter {

    private IView iview;
    private List<Food> allFoodList;


    public AddFoodPresenter(IView iview, List<Food> list) {
        this.iview = iview;
        allFoodList = new ArrayList<>();
        if (list != null)               //loading list if it is not empty
            allFoodList.addAll(list);
    }

    public List<Food> getAllFoodList() {
        return allFoodList;
    }

    public interface IView {
        void hideDeleteButton();
    }
}
