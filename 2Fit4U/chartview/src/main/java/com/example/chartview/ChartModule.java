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
import com.example.core.items.HipItem;
import com.example.core.items.NeckItem;
import com.example.core.items.WaistItem;
import com.example.core.items.WeightItem;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
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
    private List<HipItem> hipItemList;
    private List<WeightItem> weightItemList;
    private List<NeckItem> neckItemList;
    private List<WaistItem> waistItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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
        return context.getDrawable(R.drawable.icon_charts);
    }

    @Override
    public String getName(Context context) {
        return "Chart view";
    }

    @Override
    public void setWeightData(List<WeightItem> weights) {
        this.weightItemList = weights;
    }

    @Override
    public void setHipData(List<HipItem> hips) {
        this.hipItemList = hips;
    }

    @Override
    public void setNeckData(List<NeckItem> necks) {
        this.neckItemList = necks;
    }

    @Override
    public void setWaistData(List<WaistItem> waists) {
        this.waistItemList = waists;
    }


    private void tryToDisplayData() {
        if(moduleReadyFlag)
        {
            displayData();
        }
    }

    private void displayData() {


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(generateHipDataSet());
        dataSets.add(generateNeckDataSet());
        dataSets.add(generateWaistDataSet());
        dataSets.add(generateWeightDataSet());

        LineData data = new LineData(dataSets);

        mChart.setData(data);
    }

    private ILineDataSet generateWeightDataSet()
    {

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0;i<weightItemList.size();i++)
        {
            entries.add(new Entry(i,Float.parseFloat(weightItemList.get(i).getWeightValue())));
        }

        LineDataSet set = new LineDataSet(entries, "Weight");
        set.setColor(Color.BLUE);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.BLUE);
        set.setCircleRadius(5f);
        set.setFillColor(Color.BLUE);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }

    private ILineDataSet generateNeckDataSet()
    {

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0;i<neckItemList.size();i++)
        {
            entries.add(new Entry(i,Float.parseFloat(neckItemList.get(i).getNeckValue())));
        }

        LineDataSet set = new LineDataSet(entries, "Neck");
        set.setColor(Color.RED);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.RED);
        set.setCircleRadius(5f);
        set.setFillColor(Color.RED);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }

    private ILineDataSet generateHipDataSet()
    {

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0;i<hipItemList.size();i++)
        {
            entries.add(new Entry(i,Float.parseFloat(hipItemList.get(i).getHipValue())));
        }

        LineDataSet set = new LineDataSet(entries, "Hip");
        set.setColor(Color.GREEN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }

    private ILineDataSet generateWaistDataSet()
    {

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0;i<waistItemList.size();i++)
        {
            entries.add(new Entry(i,Float.parseFloat(waistItemList.get(i).getWaistValue())));
        }

        LineDataSet set = new LineDataSet(entries, "Waist");
        set.setColor(Color.GRAY);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GRAY);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GRAY);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }


}
