package com.example.biciclik.Maps;

import com.example.biciclik.objects.PointData;

import java.util.ArrayList;

public class MapsPresenters implements MapsInterfaces.presenters{
    private MapsInterfaces.activities view;
    private MapsModels model;

    public MapsPresenters(MapsInterfaces.activities view) {
        this.view = view;
        this.model= new MapsModels();
    }

    @Override
    public void getListPointsPresenter() {
        model.getListPointsModel(this);
    }

    @Override
    public void onSuccessListPresenter(ArrayList<PointData> results) {
        view.setListPoints(results);
    }

}
