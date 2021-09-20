package com.colombiagames.biciclick.Maps;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.PointData;
import com.colombiagames.biciclick.objects.PointsResponse;

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
                    localData.registerrRetry(0);
                    presenter.onSuccessListPresenter(objects_list.getResults());
                }else {
                    if (localData.getRegisterRetry()==0 || localData.getRegisterRetry()==1){
                        try {
                            Thread.sleep(500);
                            if (localData.getRegisterRetry()==0){
                                localData.registerrRetry(1);
                                getListPointsModel(presenter);
                            }else {
                                localData.registerrRetry(2);
                                getListPointsModel(presenter);
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        localData.registerrRetry(0);
                        localData.LogOutApp();
                        Log.e("mapsmodels", "getlist");
                        localData.register("", "ID_REGISTER_PUSH");
                        presenter.login();
                    }
                }
            }

            @Override
            public void onFailure(Call<PointsResponse> call, Throwable t) {

            }
        });
    }

}
