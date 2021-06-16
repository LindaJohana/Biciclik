package com.example.biciclik.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.R;
import com.example.biciclik.objects.PersonResponse;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class HomeActivity extends Fragment {
    private PieChart pieChart;
    private HomeAdapter inicioAdapter;
    RecyclerView recyclerView;
    ArrayList<PersonResponse> listPersons;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home,container,false);
        listPersons=new ArrayList<PersonResponse>();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclershippings);

        setListPersons();
        mostrar();
        return view;
//        pieChart= (PieChart) view.findViewById(R.id.piechart);
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
