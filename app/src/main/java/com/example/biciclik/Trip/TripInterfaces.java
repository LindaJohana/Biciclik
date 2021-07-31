package com.example.biciclik.Trip;

import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.objects.TripResponseFinal;

public interface TripInterfaces {
    interface activities{
        void setTrip(TripResponseFinal data);
        void lanzarLogin();
        void lanzarHome();
    }
    interface presenters{
        void getInfoTripPresenter();
        void setInfoTripPresenter(TripResponseFinal data);
        void sendStatusPresenter(String UrlPhoto);
        void login();
        void home();
    }
    interface models{
        void getInfoTripModel(presenters presenter);
        void sendStatusModel(presenters presenter, String UrlPhoto);
    }
}
