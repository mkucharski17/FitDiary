package com.example.android.fitdiary.Day.TrainingDay.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.fitdiary.Day.TrainingDay.Presenters.BarChartPresenter;
import com.example.android.fitdiary.Views.BaseFragment;
import com.example.android.fitdiary.Day.TrainingDay.Models.Exercise;
import com.example.android.fitdiary.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.List;

/*
 * Fragment using to show bar chart
 * */

public class BarChartFragment extends BaseFragment implements BarChartPresenter.Iview {

    private BarChart barChart;
    private BarChartPresenter presenter;

    public BarChartFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        barChart = v.findViewById(R.id.barchart);
        presenter = new BarChartPresenter(this, (List<Exercise>) getArguments().getSerializable("exercises"));
        presenter.createChart();
        loadChart();
        return v;
    }

    private void loadChart() {
        barChart.setData(presenter.getData());
        barChart.setDescription(null);
        barChart.animateY(1000);
    }


}
