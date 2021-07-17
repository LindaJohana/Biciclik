package com.example.biciclik.Home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
import com.example.biciclik.objects.StatisticsData;
import com.example.biciclik.objects.TravelTopData;
import com.example.biciclik.utils.FontManager;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class HomeActivity extends Fragment implements HomeInterfaces.activities{
    public PieChart pieChart1;
    public PieChart pieChart2;
    public PieChart pieChart3;
    public BarChart barChart;
    private HomeAdapter inicioAdapter;
    RecyclerView recyclerView;
    ArrayList<PersonResponse> listPersons;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA", "android.permission.CALL_PHONE"};
    HomePresenters presenter;
    int requestCode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home,container,false);
        initObjects(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        presenter.TopCompanyPresenter();
        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
        presenter.TravelMonthPresenter();
        loadPieChartData(pieChart1,0xFF66FFCC);
        loadPieChartData(pieChart2,0xFFE74C3C);
        loadPieChartData(pieChart3,0xFF3399FF);
        presenter.travelStatisticsPresenter();

        return view;
    }
    private void initObjects(View view){
        requestCode = 200;
        presenter=new HomePresenters(this);
        listPersons=new ArrayList<PersonResponse>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclershippings);
        pieChart1 = view.findViewById(R.id.piechart1);
        pieChart2 = view.findViewById(R.id.piechart2);
        pieChart3 = view.findViewById(R.id.piechart3);
        barChart=view.findViewById(R.id.barChart);
    }
    public void setupBar(ArrayList<Integer> results){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i=0; i<results.size();i++){
            entries.add(new BarEntry(i+1, results.get(i)));
        }
        BarDataSet bardataset = new BarDataSet(entries, "");
        Legend l=barChart.getLegend();
        l.setEnabled(false);
        BarData data = new BarData(bardataset);
        barChart.setData(data);
        bardataset.setColors (0xFFE74C3C);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setPinchZoom(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawLabels(true);
        barChart.setFitBars(true);
        barChart.getAxisLeft().setDrawLabels(true);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.setVisibleXRangeMaximum((float) 7.0);


        //barChart.setDrawGridBackground(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        barChart.animateY (1400);
    }

    @Override
    public void setErrorTravelMonth(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serErrorTravelStatistics(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void travelStatisticsResults(StatisticsData result) {
        setupPieChart(pieChart1, String.valueOf(result.getTrips()));
        setupPieChart(pieChart2, String.valueOf(result.getEconomic_savings()));
        setupPieChart(pieChart3, String.valueOf(result.getCarbon_footprint()));
    }

    private void setupPieChart(PieChart pieChart, String result) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(70);

        //pieChart.setUsePercentValues(true);
//        pieChart.setEntryLabelTextSize(12);
//        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(result);
//        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
//        FontManager.markAsIconContainer(pieChart, iconFont);
        pieChart.setCenterTextSize(12);
        pieChart.getDescription().setEnabled(false);
        Legend l = pieChart.getLegend();
        /*l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);*/
        //l.setDrawInside(false);
        l.setEnabled(false);
    }
    public void loadPieChartData(PieChart pieChart, int color) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(1f));
        entries.add(new PieEntry(0f));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(color);
        colors.add(0xffc5c6c8);
        /*for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }*/
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);
        data.setValueFormatter(new PercentFormatter(pieChart));
        /*data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);*/
//        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
//        FontManager.markAsIconContainer(pieChart, iconFont);
        pieChart.setData(data);
//        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    public void TopCompanyPersons(ArrayList<TravelTopData> results){
        inicioAdapter = new HomeAdapter(getContext(), results);
        recyclerView.setAdapter(inicioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public void setErrorTravelTop(String message) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
