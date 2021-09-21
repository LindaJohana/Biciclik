package com.colombiagames.biciclick.Trip;

import android.util.Log;

import com.colombiagames.biciclick.objects.TripResponseFinal;

public class TripPresenters implements TripInterfaces.presenters{
    private TripInterfaces.activities view;
    private TripModels model;

    public TripPresenters(TripInterfaces.activities view) {
        this.view = view;
        this.model=new TripModels();
    }

    @Override
    public void getInfoTripPresenter() {
        model.getInfoTripModel(this);
    }

    @Override
    public void setInfoTripPresenter(TripResponseFinal data) {
        view.setTrip(data);
    }

    @Override
    public void sendStatusPresenter(String UrlPhoto) {
        model.sendStatusModel(this, UrlPhoto);
    }

    @Override
    public void login() {
        view.lanzarLogin();
    }

    @Override
    public void home() {
        view.lanzarHome();
    }

}
