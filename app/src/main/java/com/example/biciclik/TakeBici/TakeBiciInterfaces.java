package com.example.biciclik.TakeBici;

import com.example.biciclik.objects.BikeData;
import com.example.biciclik.objects.CreateTripData;
import com.example.biciclik.objects.TripResponse;

import java.text.ParseException;

public interface TakeBiciInterfaces {
    interface activities{
        void sesionCod(BikeData data);
        void setErrorCod(String message);
        void setErrorTrip(String message);
        void mostrarFragment(TripResponse data);
    }
    interface fragment{
        void setData(String point, String  date);
        void lanzarLogin();
    }
    interface presenters{
        void sendCodPresenter(String cod);
        void onSuccesCod(BikeData data);
        void onErrorCod(String message);
        void createTripPresenter();
        void onErrorTrip(String message);
        void onSuccessTrip(TripResponse data);
        void codelogin();
    }
    interface models{
        void sendCodModel(presenters presenter, String cod);
        void createTripModel(presenters presenter);
    }
}
