package com.colombiagames.biciclick.TakeBici;

import com.colombiagames.biciclick.objects.BikeData;
import com.colombiagames.biciclick.objects.PatchTrip;
import com.colombiagames.biciclick.objects.PointData;
import com.colombiagames.biciclick.objects.TripResponse;
import com.colombiagames.biciclick.utils.KeyPairBoolDataCustom;

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
