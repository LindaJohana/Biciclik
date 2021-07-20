package com.example.biciclik.Trip;

import android.util.Log;

import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.objects.TripResponseFinal;

public class TripPresenters implements TripInterfaces.presenters{
    private TripInterfaces.activities view;
    private TripModels model;

    public TripPresenters(TripInterfaces.activities view) {
        this.view = view;
        this.model=new TripModels();
    }

    @Override
    public void getInfoTripPresenter() {
        Log.e("PRESENTER", "TRIP");
        model.getInfoTripModel(this);
    }

    @Override
    public void setInfoTripPresenter(TripResponseFinal data) {
        view.setTrip(data);
    }

    @Override
    public void sendStatusPresenter() {
        model.sendStatusModel(this);
    }
}
