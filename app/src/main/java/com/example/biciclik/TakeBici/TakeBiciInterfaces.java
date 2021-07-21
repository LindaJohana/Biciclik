package com.example.biciclik.TakeBici;

import com.example.biciclik.objects.BikeData;
import com.example.biciclik.objects.CompanyData;
import com.example.biciclik.objects.CreateTripData;
import com.example.biciclik.objects.PatchTrip;
import com.example.biciclik.objects.PointData;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.KeyPairBoolDataCustom;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface TakeBiciInterfaces {
    interface activities{
        void sesionCod(BikeData data);
        void setErrorCod(String message);
        void setErrorTrip(String message);
        void mostrarFragment(String date, String point, String time);
        void lanzarLogin();
    }
    interface fragment{
        void setData(String point, String  date, String time);
        void lanzarLogin();
        void showpoint(List<KeyPairBoolDataCustom> names);
        void setErrorSetTrip(String message);
    }
    interface presenters{
        void sendCodPresenter(String cod);
        void onSuccesCod(BikeData data);
        void onErrorCod(String message);
        void createTripPresenter();
        void onErrorTrip(String message);
        void onSuccessTrip(TripResponse data);
        void codelogin();
        void getDeliveryPoint();
        void setDeliveryPoint(ArrayList<PointData> points);
        void setTripPresenter(PatchTrip data);
        void onErrorSetTrip(String message);
        void login();
    }
    interface models{
        void sendCodModel(presenters presenter, String cod);
        void createTripModel(presenters presenter);
        void getDeliveryPointModel(presenters presenter);
        void setTripModel(presenters presenter, PatchTrip data);
    }
}
