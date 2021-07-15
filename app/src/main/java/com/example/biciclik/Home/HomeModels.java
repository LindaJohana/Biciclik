package com.example.biciclik.Home;

import android.util.Log;

import com.example.biciclik.Api.HomeApiAdapter;
import com.example.biciclik.Login.LoginInterfaces;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.TravelTopData;
import com.example.biciclik.utils.CustomErrorResponse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModels implements HomeInterfaces.models{
    private LoginInterfaces.models model;
    private HomeApiAdapter homeApiAdapter;
    private LocalData localData;

    public HomeModels() {
        this.model = model;
        this.homeApiAdapter = new HomeApiAdapter();
        this.localData= new LocalData();
    }
    @Override
    public void TopCompanyModel(HomeInterfaces.presenters presenter) {
        Call<ArrayList<TravelTopData>> call= homeApiAdapter.getApiService2().travelTop();
        call.enqueue(new Callback<ArrayList<TravelTopData>>() {
            @Override
            public void onResponse(Call<ArrayList<TravelTopData>> call, Response<ArrayList<TravelTopData>> response) {
                if (response.isSuccessful()){
                    ArrayList<TravelTopData> objects_list = null;
                    objects_list=response.body();
                    Log.e("HOME", objects_list.toString());
                    presenter.onSuccessTopCompany(objects_list);
                }else {
                    Log.e("else", "else home");
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorTravelTop(response_user);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TravelTopData>> call, Throwable t) {
                Log.e("Volvio a  entrar", "home");
            }
        });
    }
}
