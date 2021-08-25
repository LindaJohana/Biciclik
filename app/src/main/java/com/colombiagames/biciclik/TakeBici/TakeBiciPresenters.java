package com.colombiagames.biciclik.TakeBici;

import android.util.Log;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.colombiagames.biciclik.objects.BikeData;
import com.colombiagames.biciclik.objects.PatchTrip;
import com.colombiagames.biciclik.objects.PointData;
import com.colombiagames.biciclik.objects.TripResponse;
import com.colombiagames.biciclik.utils.KeyPairBoolDataCustom;

import java.util.ArrayList;
import java.util.List;

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
        viewK.mostrarFragment(data.getStart_date(), data.getStart_point().getName(), "");
//        viewF.setData(data);
    }

    @Override
    public void codelogin() {
        viewF.lanzarLogin();
    }

    @Override
    public void getDeliveryPoint() {
        model.getDeliveryPointModel(this);
    }

    @Override
    public void setDeliveryPoint(ArrayList<PointData> points) {
//        viewF.showpoint(points);
        List<KeyPairBoolDataCustom> listArray0 = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            PointData point = points.get(i);
            KeyPairBoolDataCustom h = new KeyPairBoolDataCustom();
            h.setId(point.getId());
            h.setExtra("lo que sea");
            h.setName(point.getName());
            h.setSelected(false);
            listArray0.add(h);
        }
        viewF.showpoint(listArray0);
    }

    @Override
    public void setTripPresenter(PatchTrip data) {
        model.setTripModel(this, data);
    }

    @Override
    public void onErrorSetTrip(String message) {
        viewF.setErrorSetTrip(message);
    }

    @Override
    public void login() {
        viewF.lanzarLogin();
    }

}
