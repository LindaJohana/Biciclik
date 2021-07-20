package com.example.biciclik.Trip;

import android.util.Log;

import com.example.biciclik.Profile.ProfileInterfaces;
import com.example.biciclik.Profile.ProfileModels;
import com.example.biciclik.objects.TripResponse;

public class TripPresenters implements TripInterfaces.presenters{
    private TripInterfaces.activities view;
    private TripModels model;

    public TripPresenters(TripInterfaces.activities view) {
        this.view = view;
        this.model=new TripModels();
    }

    @Override
    public void getTripPresenter() {
        Log.e("PRESENTER", "TRIP");
        model.getTripModel(this);
    }

    @Override
    public void setTripPresenter(TripResponse data) {
        view.setTrip(data);
    }
}
