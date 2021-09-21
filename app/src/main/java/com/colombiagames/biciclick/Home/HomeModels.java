package com.colombiagames.biciclick.Home;

import android.content.Intent;
import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.Login.LoginActivities;
import com.colombiagames.biciclick.Login.LoginInterfaces;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.ObjectPush;
import com.colombiagames.biciclick.objects.ProfileData;
import com.colombiagames.biciclick.objects.StatisticsData;
import com.colombiagames.biciclick.objects.TravelTopData;
import com.colombiagames.biciclick.utils.CustomErrorResponse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
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
                    presenter.onSuccessTopCompany(objects_list);
                    localData.registerrRetry(0);
                }else {
                    if (response.raw().code()==401){
                        if (localData.getRegisterRetry()==0 || localData.getRegisterRetry()==1){
                            try {
                                Thread.sleep(1000);
                                if (localData.getRegisterRetry()==0){
                                    localData.registerrRetry(1);
                                    TopCompanyModel(presenter);
                                }else {
                                    localData.registerrRetry(2);
                                    TopCompanyModel(presenter);
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            localData.registerrRetry(0);
                            localData.LogOutApp();
                            localData.register("", "ID_REGISTER_PUSH");
                            presenter.login();
                        }
                    }
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

            }
        });
    }

    @Override
    public void sendtokenpushModel(String tokenpush, HomeInterfaces.presenters presenter) {
        Call<ProfileData> call = HomeApiAdapter.getApiService2().tokenPush(localData.getRegister("TOKENPUSH"), "gcm");
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    localData.register(String.valueOf(response.body().getId()), "ID");
                }else {
                    CustomErrorResponse custom_error = new CustomErrorResponse();
                    String response_user = "Intentalo nuevamente";
                    try {
                        response_user = custom_error.returnMessageError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    presenter.onErrorLogout();
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
            }
        });
    }
}
