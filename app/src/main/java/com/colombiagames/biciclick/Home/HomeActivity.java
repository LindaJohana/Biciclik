package com.colombiagames.biciclick.Home;

import android.content.Intent;
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

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.PersonResponse;
import com.colombiagames.biciclick.objects.StatisticsData;
import com.colombiagames.biciclick.objects.TravelTopData;
import com.colombiagames.biciclick.utils.FontManager;
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
    LocalData localData;
    RecyclerView recyclerView;
    ArrayList<PersonResponse> listPersons;
    private String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW","android.permission.CAMERA", "android.permission.CALL_PHONE"};
    HomePresenters presenter;
    int requestCode;
    TextView txt4, txt5, txt6, txt7, txt8, txt9;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home,container,false);
        initObjects(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        Typeface fuente = Typeface.createFromAsset(getActivity().getAssets(), "fonts/verdana.ttf");
        txt4.setTypeface(fuente);
        txt5.setTypeface(fuente);
        txt6.setTypeface(fuente);
        txt7.setTypeface(fuente);
        txt8.setTypeface(fuente);
        txt9.setTypeface(fuente);

        presenter.TopCompanyPresenter();
        presenter.sendTokenPushPresenter(localData.getRegister("TOKENPUSH"));
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
        txt4=view.findViewById(R.id.txt4);
        txt5=view.findViewById(R.id.txt5);
        txt6=view.findViewById(R.id.txt6);
        txt7=view.findViewById(R.id.txt7);
        txt8=view.findViewById(R.id.txt8);
        txt9=view.findViewById(R.id.txt9);
        localData = new LocalData();
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

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void lanzarloginsinT() {
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void setupPieChart(PieChart pieChart, String result) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(70);
        pieChart.setCenterText(result);
        pieChart.getDescription().setEnabled(false);
        Legend l = pieChart.getLegend();
        l.setEnabled(false);
    }
    public void loadPieChartData(PieChart pieChart, int color) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(1f));
        entries.add(new PieEntry(0f));
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(color);
        colors.add(0xffc5c6c8);
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);
        data.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setData(data);
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
