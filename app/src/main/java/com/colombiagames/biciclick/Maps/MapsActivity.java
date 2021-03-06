package com.colombiagames.biciclick.Maps;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.colombiagames.biciclick.BaseContext.BaseContext;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.R;
import com.colombiagames.biciclick.objects.PointData;
import com.colombiagames.biciclick.objects.PuntosResponse;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

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
    TextView txt14, txt15;
    private String [] permissions = {"android.permission.ACCESS_COARSE_LOCATION","android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW"};
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map1, container, false);
        initObjects(view);

        //letra verdana
        Typeface fuente = Typeface.createFromAsset(getActivity().getAssets(), "fonts/verdana.ttf");
        txt14.setTypeface(fuente);
        txt15.setTypeface(fuente);

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        presenter.getListPointsPresenter();
        return view;
    }
    private void initObjects(View view){
        listPuntos=new ArrayList<PuntosResponse>();
        presenter = new MapsPresenters(this);
        recyclerPuntos=(RecyclerView) view.findViewById(R.id.recyclerPuntos);
        txt14 = view.findViewById(R.id.txt14);
        txt15 = view.findViewById(R.id.txt15);
    }

    public void mostrarFragment(){
        try {
            transaction=getChildFragmentManager().beginTransaction();
            transaction.add(R.id.contenedorFragments,mapsActivity);
            transaction.addToBackStack(null);
            transaction.commit();
        }catch (Exception excepcion){

        }
    }

    @Override
    public void setListPoints(ArrayList<PointData> results) {
        mapsActivity=new Maps(results);
        map=new Maps(results);
        getChildFragmentManager().beginTransaction().commit();
        mostrarFragment();
        mapsAdapter = new MapsAdapter(getContext(), results);
        recyclerPuntos.setAdapter(mapsAdapter);
        recyclerPuntos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), getString(R.string.expiroToken), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BaseContext.getContext(), LoginActivities.class );
        startActivity(i);
    }
}
