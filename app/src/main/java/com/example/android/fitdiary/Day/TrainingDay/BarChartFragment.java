package com.example.android.fitdiary.Day.TrainingDay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.fitdiary.Day.DietDay.Food;
import com.example.android.fitdiary.Day.DietDay.PieChartPresenter;
import com.example.android.fitdiary.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;

import java.util.List;

public class BarChartFragment extends BaseFragment implements BarChartPresenter.Iview{

    private BarChart barChart;
    private BarChartPresenter presenter;

    public BarChartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bar_chart, container, false);
        barChart = v.findViewById(R.id.barchart);
        presenter = new BarChartPresenter(this,(List<Exercise>) getArguments().getSerializable("exercises"));
        presenter.createChart();
        loadChart();
        return v;
    }

    private void loadChart(){
        barChart.setData(presenter.getData());
        barChart.setDescription(null);
        barChart.animateY( 1000);
    }


}
