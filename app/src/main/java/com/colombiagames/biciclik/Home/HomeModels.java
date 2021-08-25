package com.colombiagames.biciclik.Home;

import android.util.Log;

import com.colombiagames.biciclik.Api.HomeApiAdapter;
import com.colombiagames.biciclik.Login.LoginInterfaces;
import com.colombiagames.biciclik.local_data.LocalData;
import com.colombiagames.biciclik.objects.StatisticsData;
import com.colombiagames.biciclik.objects.TravelTopData;
import com.colombiagames.biciclik.utils.CustomErrorResponse;

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
                    localData.registerrRetry(0);
                }else {
                    if (response.raw().code()==401){
                        if (localData.getRegisterRetry()==0){
                            Log.e("primer if","RETRY=0");
                            try {
                                Thread.sleep(1000);
                                localData.registerrRetry(1);
                                TopCompanyModel(presenter);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.e("else","RETRY=1");
                            localData.registerrRetry(0);
                            localData.LogOutApp();
                            presenter.login();
                        }
                    }
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

    @Override
    public void TravelMonthModel(HomeInterfaces.presenters presenter) {
        Call<ArrayList<Integer>> call= homeApiAdapter.getApiService2().travelMonth();
        call.enqueue(new Callback<ArrayList<Integer>>() {
            @Override
            public void onResponse(Call<ArrayList<Integer>> call, Response<ArrayList<Integer>> response) {
                if (response.isSuccessful()){
                    ArrayList<Integer> object_list=null;
                    object_list= response.body();
                    presenter.onSuccessTravelMonth(object_list);
                    localData.registerrRetry(0);
                }else{
                    Log.e("else", "else home");
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorTravelMonth(response_user);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Integer>> call, Throwable t) {

            }
        });
    }

    @Override
    public void travelStatisticsModel(HomeInterfaces.presenters presenter) {
        Call<StatisticsData>call=homeApiAdapter.getApiService2().travelStatistics();
        call.enqueue(new Callback<StatisticsData>() {
            @Override
            public void onResponse(Call<StatisticsData> call, Response<StatisticsData> response) {
                if (response.isSuccessful()){
                    presenter.onSuccessTravelStatistics(response.body());
                    localData.registerrRetry(0);
                }else{

                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorTravelStatistics(response_user);
                }
            }
            @Override
            public void onFailure(Call<StatisticsData> call, Throwable t) {
                Log.e("ONFAIRULE HOME STADIST", t.toString());
            }
        });
    }
}
