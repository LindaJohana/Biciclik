package com.example.biciclik.Home;

import android.util.Log;

import com.example.biciclik.Api.HomeApiAdapter;
import com.example.biciclik.Api.LoginAdapter;
import com.example.biciclik.Login.LoginInterfaces;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.CompanyResponse;
import com.example.biciclik.objects.LoginResponse;
import com.example.biciclik.objects.ResultsResponse;
import com.example.biciclik.objects.ResultsTopHome;
import com.example.biciclik.objects.TokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

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
        Call<ResultsTopHome> call= homeApiAdapter.getApiService2().topCompanyTrip("3");
        call.enqueue(new Callback<ResultsTopHome>() {
            @Override
            public void onResponse(Call<ResultsTopHome> call, Response<ResultsTopHome> response) {
                if (response.isSuccessful()){
                    ResultsTopHome objects_list = null;
                    objects_list=response.body();
                    Log.e("HOME", objects_list.getResults().toString());
                    presenter.onSuccessTopCompany(objects_list.getResults());
                }else {
                    Log.e("else", "else home");
                }
            }
            @Override
            public void onFailure(Call<ResultsTopHome> call, Throwable t) {

                Log.e("Volvio a  entrar", "home");
            }
        });
    }
}
