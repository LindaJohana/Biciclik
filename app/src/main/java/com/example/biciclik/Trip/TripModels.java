package com.example.biciclik.Trip;

import android.util.Log;

import com.example.biciclik.Api.HomeApiAdapter;
import com.example.biciclik.TakeBici.TakeBiciInterfaces;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.TripResponse;
import com.example.biciclik.utils.CustomErrorResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripModels implements TripInterfaces.models{
    private TripInterfaces.models model;
    private HomeApiAdapter homeApiAdapter;
    private LocalData localData;

    public TripModels() {
        this.model = model;
        this.homeApiAdapter=new HomeApiAdapter();
        this.localData=new LocalData();
    }

    @Override
    public void getTripModel(TripInterfaces.presenters presenter) {
        Log.e("MODEL", "TRIP");
        Call<TripResponse> call=homeApiAdapter.getApiService2().finalTrip(localData.getRegister("ID_TRIP"));
        call.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    TripResponse objects_list = null;
                    objects_list=response.body();
                    presenter.setTripPresenter(objects_list);
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("MODEL ERROR", "TRIP");
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {
                Log.e("ONFAIRULE", "TRIPMODEL");
            }
        });
    }
}
