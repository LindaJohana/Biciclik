package com.example.biciclik.Maps;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biciclik.R;
import com.example.biciclik.objects.PointData;
import com.example.biciclik.objects.PuntosResponse;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationSettingsRequest;

import java.util.ArrayList;

public class MapsActivity extends Fragment implements MapsInterfaces.activities {
    private static final String TAG = "Map";
    private MapsAdapter mapsAdapter;
    private Maps map;
    RecyclerView recyclerPuntos;
    FragmentTransaction transaction;
    Fragment mapsActivity;
    ArrayList<PuntosResponse> listPuntos;
    MapsPresenters presenter;
    private LocationCallback locationCallback;
    private String [] permissions = {"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW"};
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map1, container, false);
        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        listPuntos=new ArrayList<PuntosResponse>();
        presenter = new MapsPresenters(this);
        presenter.getListPointsPresenter();
        recyclerPuntos=(RecyclerView) view.findViewById(R.id.recyclerPuntos);
        mapsActivity=new Maps();
        getChildFragmentManager().beginTransaction().commit();
        mostrarFragment();

        return view;
    }
//    public void setListPuntos(){
//        listPuntos.add(new PuntosResponse("Punto 1",  8));
//        listPuntos.add(new PuntosResponse("Punto 2", 127));
//        listPuntos.add(new PuntosResponse("Punto 3",  28));
//    }
    /*public void setListPersons(ArrayList<PersonResponse> notes){
        this.listPersons.clear();
        this.listPersons.addAll(notes);
    }*/

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

    @Override
    public void setListPoints(ArrayList<PointData> results) {
        map=new Maps();
        map.puntos(results);
        mapsAdapter = new MapsAdapter(getContext(), results);
        recyclerPuntos.setAdapter(mapsAdapter);
        recyclerPuntos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}
