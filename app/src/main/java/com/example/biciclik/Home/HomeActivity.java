package com.example.biciclik.Home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.BaseContext.BaseContext;
import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.common.util.Hex;

import java.util.ArrayList;

public class HomeActivity extends Fragment implements HomeInterfaces.activities{
    public PieChart pieChart1;
    public PieChart pieChart2;
    public PieChart pieChart3;
    public BarChart barChart;
    public TextView viajes;
    private HomeAdapter inicioAdapter;
    RecyclerView recyclerView;
    ArrayList<PersonResponse> listPersons;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home,container,false);
        listPersons=new ArrayList<PersonResponse>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclershippings);
        pieChart1 = view.findViewById(R.id.piechart1);
        pieChart2 = view.findViewById(R.id.piechart2);
        pieChart3 = view.findViewById(R.id.piechart3);
        viajes = view.findViewById(R.id.viajes);
        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(view.findViewById(R.id.viajes), iconFont);
        viajes.setTextColor(Color.RED);
        setupPieChart(pieChart1);
        setupPieChart(pieChart2);
        setupPieChart(pieChart3);
        loadPieChartData(pieChart1, 0.7f, 0.3f, 0xFF66FFCC);
        loadPieChartData(pieChart2, 0.5f, 0.5f, 0xFFE74C3C);
        loadPieChartData(pieChart3,0.9f, 0.1f, 0xFF3399FF);
        setListPersons();
        mostrar();
        barChart=view.findViewById(R.id.barChart);
        setupBar();
        return view;
    }
    private void setupBar(){
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, 20));
        entries.add(new BarEntry(2f, 30));
        entries.add(new BarEntry(3f, 6));
        entries.add(new BarEntry(4f, 15));
        entries.add(new BarEntry(5f, 4));
        entries.add(new BarEntry(6f, 10));
        entries.add(new BarEntry(7f, 30));
        entries.add(new BarEntry(8f, 6));
        entries.add(new BarEntry(9f, 15));
        entries.add(new BarEntry(10f, 4));
        entries.add(new BarEntry(11f, 10));
        BarDataSet bardataset = new BarDataSet(entries, "");
        Legend l=barChart.getLegend();
        l.setEnabled(false);
        BarData data = new BarData(bardataset);
        barChart.setData(data);
        bardataset.setColors (0xFFE74C3C);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setPinchZoom(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);


        //barChart.setDrawGridBackground(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getDescription().setEnabled(false);
        barChart.animateY (1400);


    }
    private void setupPieChart(PieChart pieChart) {
        pieChart.setDrawHoleEnabled(true);

        pieChart.setHoleRadius(70);

        //pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("&#xf200;");

        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(pieChart, iconFont);
        pieChart.setCenterTextSize(8);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        /*l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);*/
        //l.setDrawInside(false);
        l.setEnabled(false);
    }
    private void loadPieChartData(PieChart pieChart, float v1, float v2, int color) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(v1));
        entries.add(new PieEntry(v2));


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
        Typeface iconFont = FontManager.getTypeface(BaseContext.getContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(pieChart, iconFont);
        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    public void setListPersons(){
        listPersons.add(new PersonResponse("Linda", "Patarroyo", 8));
        listPersons.add(new PersonResponse("johana", "Patarroyo", 127));
        listPersons.add(new PersonResponse("acero", "Patarroyo", 28));
    }
    /*public void setListPersons(ArrayList<PersonResponse> notes){
        this.listPersons.clear();
        this.listPersons.addAll(notes);
    }*/
    public void mostrar(){
        inicioAdapter = new HomeAdapter(getContext(), listPersons);
        recyclerView.setAdapter(inicioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
