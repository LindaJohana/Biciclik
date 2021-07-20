package com.example.biciclik.Trip;

import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.objects.TripResponseFinal;

public interface TripInterfaces {
    interface activities{
        void setTrip(TripResponseFinal data);
    }
    interface presenters{
        void getInfoTripPresenter();
        void setInfoTripPresenter(TripResponseFinal data);
        void sendStatusPresenter();
    }
    interface models{
        void getInfoTripModel(presenters presenter);
        void sendStatusModel(presenters presenter);
    }
}
