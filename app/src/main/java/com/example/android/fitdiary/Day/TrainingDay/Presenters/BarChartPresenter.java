package com.example.android.fitdiary.Day.TrainingDay.Presenters;

import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/*
* BarChartPresenter - presenter for BarCHartActivity, using to load data which will be used to created diagram
* */

public class BarChartPresenter {

    private List<Exercise> exercises;
    private BarData data;
    private Iview iview;

    public BarChartPresenter(Iview iview, List<Exercise> exercises) {

        this.exercises = new ArrayList<>();
        if (exercises != null)
            this.exercises.addAll(exercises);

        this.iview = iview;
    }

    public BarData getData() {
        return data;
    }

    public List<Exercise> getFood() {
        return exercises;
    }

    /*
    *  compute volume of each exercise  and add it to a list with exercise name as a title
    * */
    public void createChart() {
        int volume;
        ArrayList<BarEntry> volumes = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < exercises.size(); i++) {
            titles.add(exercises.get(i).getName());
            volume = exercises.get(i).getReps() * exercises.get(i).getSets() * exercises.get(i).getWeight();
            volumes.add(new BarEntry(volume, i));
        }

        BarDataSet dataSet = new BarDataSet(volumes, "Workout volume");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        data = new BarData(titles, dataSet);
    }


    public interface Iview {

    }

}
