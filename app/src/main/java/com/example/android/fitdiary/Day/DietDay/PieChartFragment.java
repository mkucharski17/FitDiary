package com.example.android.fitdiary.Day.DietDay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.fitdiary.Day.TrainingDay.BaseFragment;
import com.example.android.fitdiary.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;


public class PieChartFragment extends BaseFragment implements PieChartPresenter.Iview {
    private PieChart pieChart;
    private PieChartPresenter presenter;

    public PieChartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pieChart = v.findViewById(R.id.piechart);
        presenter = new PieChartPresenter(this,(List<Food>) getArguments().getSerializable("food"));
        presenter.setChart();

        loadPieChart();
        setLegend();

        return v;
    }

    private void loadPieChart(){
        pieChart.setData(presenter.getData());
        pieChart.setDescription(null);
        pieChart.animateXY(1000, 1000);
    }

    private void setLegend(){
        Legend l = pieChart.getLegend();
        l.setTextSize(15);
        l.setPosition(Legend.LegendPosition.PIECHART_CENTER);
    }

}
