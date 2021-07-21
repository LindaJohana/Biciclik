package com.example.biciclik.Maps;

import com.example.biciclik.objects.PointData;
import com.example.biciclik.objects.PointsResponse;

import java.util.ArrayList;

public interface MapsInterfaces {
    interface activities{
        void setListPoints(ArrayList<PointData> results);
        void lanzarLogin();

    }
    interface presenters{
        void getListPointsPresenter();
        void onSuccessListPresenter(ArrayList<PointData> results);
        void login();
    }
    interface models{
        void getListPointsModel(presenters presenter);
    }
}
