package com.example.android.fitdiary.Day.DietDay;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartPresenter {
    private List<Food> food;
    private PieDataSet dataSet;
    private PieData data;
    private Iview iview;

    PieChartPresenter(Iview iview, List<Food> food){

        this.food = new ArrayList<>();
        if(food != null)
            this.food.addAll(food);

        this.iview = iview;
    }

    public PieData getData() {
        return data;
    }

    public List<Food> getFood() {
        return food;
    }

    public int computeeKcal(){
        int kcal = 0;
        for(int i = 0 ; i < food.size() ; i++){
            kcal+=food.get(i).getKcal();
        }
        return kcal;
    }

    public void setChart(){
        macrosList(computeMacros());
        titles();
    }

    public MacroComponents computeMacros(){
        float protein = 0;
        float fat = 0;
        float carbs = 0;

        for(int i = 0 ; i < food.size() ; i++){
            protein += food.get(i).getMacroComponents().getProtein()*food.get(i).getPortion();
            fat += food.get(i).getMacroComponents().getFat()*food.get(i).getPortion();
            carbs += food.get(i).getMacroComponents().getCarbohydrates()*food.get(i).getPortion();
        }

        return new MacroComponents(carbs,protein,fat);
    }

    public void macrosList(MacroComponents macroComponents){
        ArrayList macros = new ArrayList();
        macros.add(new Entry(macroComponents.getCarbohydrates(), 0));
        macros.add(new Entry(macroComponents.getProtein(), 1));
        macros.add(new Entry(macroComponents.getFat(), 2));

        dataSet = new PieDataSet(macros,computeeKcal() + "kcal" );
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }

    public void titles(){
        ArrayList titles = new ArrayList();
        titles.add("carbohydrates");
        titles.add("protein");
        titles.add("fat");

        data = new PieData(titles,dataSet);

    }

    public interface Iview{

    }

}
