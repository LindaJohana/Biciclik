package com.example.biciclik.Maps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.MapsActivity;
import com.example.biciclik.R;
import com.example.biciclik.Register.Register2Activity;
import com.example.biciclik.objects.PuntosResponse;

import java.util.ArrayList;

public class Map1Activity extends Fragment {
    private static final String TAG = "Map";
    private Maps1Adapter maps1Adapter;
    RecyclerView recyclerPuntos;
    FragmentTransaction transaction;
    Fragment mapsActivity;
    ArrayList<PuntosResponse> listPuntos;
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map1, container, false);
        listPuntos=new ArrayList<PuntosResponse>();
        recyclerPuntos=(RecyclerView) view.findViewById(R.id.recyclerPuntos);
        setListPuntos();
        mostrar();
        mapsActivity=new MapsActivity();
        getChildFragmentManager().beginTransaction().commit();
        mostrarFragment();
        return view;
    }
    public void setListPuntos(){
        listPuntos.add(new PuntosResponse("Punto 1",  8));
        listPuntos.add(new PuntosResponse("Punto 2", 127));
        listPuntos.add(new PuntosResponse("Punto 3",  28));
    }
    /*public void setListPersons(ArrayList<PersonResponse> notes){
        this.listPersons.clear();
        this.listPersons.addAll(notes);
    }*/
    public void mostrar(){
        maps1Adapter = new Maps1Adapter(getContext(), listPuntos);
        recyclerPuntos.setAdapter(maps1Adapter);
        recyclerPuntos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    public void mostrarFragment(){
        try {
            transaction=getChildFragmentManager().beginTransaction();
            transaction.add(R.id.contenedorFragments,mapsActivity);
            transaction.addToBackStack(null);
            transaction.commit();
        }catch (Exception excepcion){
            Log.e(TAG, "error");
        }
    }
}
