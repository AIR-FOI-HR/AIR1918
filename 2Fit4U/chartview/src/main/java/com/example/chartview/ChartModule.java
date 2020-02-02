package com.example.chartview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.core.interfaces.DataPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartModule extends Fragment implements DataPresenter {
    private static final String TAG = "ChartActivity";

    private LineChart mChart;
    private boolean moduleReadyFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //TODO
        //Logika za chart ovdje
        super.onViewCreated(view, savedInstanceState);

        mChart = view.findViewById(R.id.linechart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        moduleReadyFlag = true;
        tryToDisplayData();
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getDrawable(android.R.drawable.ic_menu_help);
    }

    @Override
    public String getName(Context context) {
        return "Chart view";
    }

    @Override
    public void setWeightData(List<String> weights) {

    }

    @Override
    public void setHipData(List<String> hips) {

    }

    private void tryToDisplayData() {
        if(moduleReadyFlag)
        {
            displayData();
        }
    }

    private void displayData() {

        ArrayList<Entry> yValue = new ArrayList<>();

        yValue.add(new Entry(0,60f));
        yValue.add(new Entry(1,50f));
        yValue.add(new Entry(2,70f));
        yValue.add(new Entry(3,30f));
        yValue.add(new Entry(4,90f));

        LineDataSet set1 = new LineDataSet(yValue,"Data Set 1");
        set1.setFillAlpha(110);
        set1.setFillColor(Color.RED);
        set1.setLineWidth(30);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);
    }



    @Override
    public void setNeckData(List<String> necks) {

    }

    @Override
    public void setWaistData(List<String> waists) {

    }
}
