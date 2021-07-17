package com.example.biciclik.TakeBici;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.biciclik.R;
import com.example.biciclik.objects.BikeData;
import com.example.biciclik.objects.TripResponse;

public class TakeBiciPresenters implements TakeBiciInterfaces.presenters{
    private TakeBiciInterfaces.fragment viewF;
    private TakeBiciInterfaces.activities viewK;
    private TakeBiciModels model;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public TakeBiciPresenters(TakeBiciInterfaces.activities viewK,TakeBiciInterfaces.fragment viewF) {
        this.viewF = viewF;
        this.viewK = viewK;
        this.model = new TakeBiciModels();
    }

    @Override
    public void sendCodPresenter(String cod) {
        Log.e("BIKE PRESENTER", "PRESENTER");
        model.sendCodModel(this, cod);
    }

    @Override
    public void onSuccesCod(BikeData data) {
        viewK.sesionCod(data);
    }

    @Override
    public void onErrorCod(String message) {
        viewK.setErrorCod(message);
    }

    @Override
    public void createTripPresenter() {
        Log.e("PRESENTER DE CREAT", "MOSTRAR");
        model.createTripModel(this);
    }

    @Override
    public void onErrorTrip(String message) {
        viewK.setErrorTrip(message);
    }

    @Override
    public void onSuccessTrip(TripResponse data) {
        viewK.mostrarFragment(data);
//        viewF.setData(data);
    }

    @Override
    public void codelogin() {
        viewF.lanzarLogin();
    }

}
