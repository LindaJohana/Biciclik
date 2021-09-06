package com.colombiagames.biciclick.DrawerMain;

import android.util.Log;

import com.colombiagames.biciclick.Api.HomeApiAdapter;
import com.colombiagames.biciclick.local_data.LocalData;
import com.colombiagames.biciclick.objects.ProfileData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrawerModels implements DrawerInterfaces.models {
    private DrawerInterfaces.models model;
    private LocalData localData;
    private HomeApiAdapter homeApiAdapter;

    public DrawerModels() {
        this.model = model;
        this.localData = new LocalData();
        this.homeApiAdapter = new HomeApiAdapter();
    }

    @Override
    public void logOutModels(DrawerInterfaces.presenters presenter) {
        localData.LogOutApp();
    }

    @Override
    public void profileHeaderModel(DrawerInterfaces.presenters presenter) {
        Call<ProfileData> call = homeApiAdapter.getApiService2().profile();
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    localData.register(response.body().getId(),"ID");
                    presenter.onSuccessProfileHeader(response.body());
                }else {
                    if (response.raw().code()==401){
                        localData.LogOutApp();
                        presenter.codelogin();
                    }
                    Log.e("drawer perfil","ERROR");
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                Log.e("DRAWER PERFIL","FAILURE");
            }
        });
    }

    @Override
    public void verifiedModel(DrawerInterfaces.presenters presenter) {
        Call<ProfileData> call = homeApiAdapter.getApiService2().profile();
        call.enqueue(new Callback<ProfileData>() {
            @Override
            public void onResponse(Call<ProfileData> call, Response<ProfileData> response) {
                if (response.isSuccessful()){
                    presenter.verifiedSuccess(response.body().getVerified());
                }else {
                    if (response.raw().code()==401){
                        localData.LogOutApp();
                    }
                    Log.e("drawer perfil","ERROR");
                }
            }

            @Override
            public void onFailure(Call<ProfileData> call, Throwable t) {
                Log.e("DRAWER PERFIL","FAILURE");
            }
        });
    }


}
