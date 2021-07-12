package com.example.biciclik.Maps;

import android.util.Log;

import com.example.biciclik.Api.HomeApiAdapter;
import com.example.biciclik.local_data.LocalData;
import com.example.biciclik.objects.PointData;
import com.example.biciclik.objects.PointsResponse;
import com.example.biciclik.objects.PuntosResponse;
import com.example.biciclik.objects.ResultsTopHome;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsModels implements MapsInterfaces.models{
    MapsInterfaces.models model;
    private HomeApiAdapter homeApiAdapter;
    private LocalData localData;
    ArrayList<PointData> data;
    private PointsResponse objects_list;
    public MapsModels() {
        this.model=model;
        this.homeApiAdapter=new HomeApiAdapter();
        this.localData=new LocalData();
    }

    @Override
    public void getListPointsModel(MapsInterfaces.presenters presenter) {
        Call<PointsResponse> call=homeApiAdapter.getApiService2().listMaps();
        call.enqueue(new Callback<PointsResponse>() {
            @Override
            public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                if (response.isSuccessful()){
                    objects_list = null;
                    objects_list=response.body();
                    Log.e("HOME", objects_list.getResults().toString());
                    presenter.onSuccessListPresenter(objects_list.getResults());
                }else {

                }
            }

            @Override
            public void onFailure(Call<PointsResponse> call, Throwable t) {

            }
        });
    }

}
