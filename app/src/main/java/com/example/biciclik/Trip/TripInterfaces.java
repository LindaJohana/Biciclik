package com.example.biciclik.Trip;

import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.objects.TripResponseFinal;

public interface TripInterfaces {
    interface activities{
        void setTrip(TripResponseFinal data);
        void lanzarLogin();
    }
    interface presenters{
        void getInfoTripPresenter();
        void setInfoTripPresenter(TripResponseFinal data);
        void sendStatusPresenter();
        void login();
    }
    interface models{
        void getInfoTripModel(presenters presenter);
        void sendStatusModel(presenters presenter);
    }
}
