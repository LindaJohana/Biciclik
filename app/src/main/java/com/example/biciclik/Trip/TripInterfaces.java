package com.example.biciclik.Trip;

import com.example.biciclik.objects.TripResponse;

public interface TripInterfaces {
    interface activities{
        void setTrip(TripResponse data);
    }
    interface presenters{
        void getTripPresenter();
        void setTripPresenter(TripResponse data);
    }
    interface models{
        void getTripModel(presenters presenter);
    }
}
