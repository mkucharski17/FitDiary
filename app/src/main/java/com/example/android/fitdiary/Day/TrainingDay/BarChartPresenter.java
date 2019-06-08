package com.example.android.fitdiary.Day.TrainingDay;

import com.example.android.fitdiary.Day.DietDay.Food;
import com.example.android.fitdiary.Day.DietDay.MacroComponents;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BarChartPresenter {

    private List<Exercise> exercises;
    private BarDataSet dataSet;
    private BarData data;
    private Iview iview;

    BarChartPresenter(Iview iview, List<Exercise> exercises){

        this.exercises = new ArrayList<>();
        if(exercises != null)
            this.exercises.addAll(exercises);

        this.iview = iview;
    }

    public BarData getData() {
        return data;
    }

    public List<Exercise> getFood() {
        return exercises;
    }

    public void createChart( ){
        int volume;
        ArrayList volumes = new ArrayList();
        ArrayList titles = new ArrayList();
        for(int i = 0 ; i < exercises.size() ; i++){
            titles.add(exercises.get(i).getName());
            volume = exercises.get(i).getReps() * exercises.get(i).getSets()*exercises.get(i).getWeight();
            volumes.add(new BarEntry(volume, i));
        }

        dataSet = new BarDataSet(volumes,"Workout volume" );
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        data = new BarData(titles,dataSet);
    }



    public interface Iview{

    }

}
